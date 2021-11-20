package pe.edu.upc.spring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Parametro;
import pe.edu.upc.spring.model.Registro;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IParametroService;
import pe.edu.upc.spring.service.IRegistroService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/registro")
public class RegistroController {
	@Autowired
	private IRegistroService rService;
	
	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IParametroService pService;
	
	
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; 
	}
	
	@RequestMapping("/")
	public String irPaginaListadoRegistros(Map<String, Object> model) {
		model.put("listaRegistros", rService.listar());
		return "listRegistro";  
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("listaParametros", pService.listar());
		
		model.addAttribute("registro", new Registro());
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("parametro", new Parametro());
		
		return "insertRegistro"; 
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Registro objRegistro, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
		{
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaParametros", pService.listar());
			
			return "insertRegistro";
		}
		else {
			boolean flag = rService.grabar(objRegistro);
			if (flag)
				return "redirect:/registro/listar";
			else {
				model.addAttribute("mensaje", "ERROR");
				return "redirect:/registro/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Registro> objRegistro = rService.listarId(id);
		if (objRegistro == null) {
			objRedir.addFlashAttribute("mensaje", "ERROR");
			return "redirect:/registro/listar";
		}
		else {
			model.addAttribute("listaUsuarios",uService.listar());
			model.addAttribute("listaParametros",pService.listar());

			if(objRegistro.isPresent())
				objRegistro.ifPresent(o -> model.addAttribute("registro",o));
			
			return "insertRegistro";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaRegistros", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaRegistros", rService.listar());
		}
		return "listRegistro";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaRegistros", rService.listar());
		return "listRegistro";
	}
	
	//busqueda por fecha
	
	@RequestMapping("/irSearch")
	public String irBuscar(Model model ) 
	{
		model.addAttribute("registro", new Registro());
		return "searchRegistro";
	}
	
	@RequestMapping("/searchRegistro")
	public String buscar(Map<String, Object> model, @ModelAttribute Registro registro ) throws ParseException
	{
		List<Registro> listaRegistros;
		registro.setFechaRegistro(registro.getFechaRegistro());
		listaRegistros = rService.findByFechaRegistro(registro.getFechaRegistro());
		
		
		if(listaRegistros.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaRegistros", listaRegistros);
		return "searchRegistro";
	}
	
	@RequestMapping("/listar2")
    public String getPieChart(Model model) {
        Map<String, Integer> graphData = new TreeMap<>();
        List<Registro> listaRegistros = rService.listar();
        
        List<Integer> valores = new ArrayList<Integer>();
        List<String> fecha = new ArrayList<String>();
        
        for(int i = 0; i<listaRegistros.size();i++) {
        	Registro r = listaRegistros.get(i);
        	valores.add(r.getNumValor());
        	SimpleDateFormat aux = new SimpleDateFormat("yyyy/MM/dd");
        	String aux2 = aux.format(r.getFechaRegistro());
        	fecha.add(aux2);
        }
        
        for(int i = 0; i<listaRegistros.size();i++) {
        	graphData.put(fecha.get(i), valores.get(i));
        }
        
        model.addAttribute("chartData", graphData);
        return "pruebaChart";
    }
	
}
