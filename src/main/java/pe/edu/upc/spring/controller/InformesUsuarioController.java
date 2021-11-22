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

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Parametro;
import pe.edu.upc.spring.model.Registro;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IParametroService;
import pe.edu.upc.spring.service.IRegistroService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/informeUsuario")
public class InformesUsuarioController {
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
	
	@RequestMapping("/mostrar")
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
        return "informeAdmin";
    }
	
}