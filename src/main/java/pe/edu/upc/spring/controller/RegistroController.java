package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
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
				model.addAttribute("mensaje", "Ocurrio un error");
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
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
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
	
}
