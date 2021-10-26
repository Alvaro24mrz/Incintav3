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

import pe.edu.upc.spring.model.Unidad;
import pe.edu.upc.spring.service.IUnidadService;

@Controller
@RequestMapping("/unidad")
public class UnidadController {
	@Autowired
	private IUnidadService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; 
	}
	
	@RequestMapping("/")
	public String irPaginaListadoUnidades(Map<String, Object> model) {
		model.put("listaUnidades", rService.listar());
		return "listUnidad";  
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("unidad", new Unidad());
		return "insertUnidad"; 
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Unidad objUnidad, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "insertUnidad";
		else {
			boolean flag = rService.grabar(objUnidad);
			if (flag)
				return "redirect:/parametro/listar";
			else {
				model.addAttribute("mensaje", "ERROR");
				return "redirect:/unidad/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Unidad> objUnidad = rService.listarId(id);
		if (objUnidad == null) {
			objRedir.addFlashAttribute("mensaje", "ERROR");
			return "redirect:/unidad/listar";
		}
		else {
			model.addAttribute("unidad",objUnidad);
			return "insertUnidad";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaUnidades", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaUnidades", rService.listar());
		}
		return "listUnidad";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaUnidades", rService.listar());
		return "listUnidad";
	}
	
	
	@RequestMapping("/irSearch")
	public String irBuscar(Model model ) 
	{
		model.addAttribute("unidad", new Unidad());
		return "searchUnidad";
	}
	
	@RequestMapping("/searchUnidad")
	public String buscar(Map<String, Object> model, @ModelAttribute Unidad unidad ) throws ParseException
	{
		
		
		List<Unidad> listaUnidades;
		unidad.setnUnidad(unidad.getnUnidad());
		listaUnidades = rService.buscarNombre(unidad.getnUnidad());
		
		
		if(listaUnidades.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaUnidades", listaUnidades);
		return "searchUnidad";
	}
	
}
