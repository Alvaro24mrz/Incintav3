package pe.edu.upc.spring.controller;

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

import pe.edu.upc.spring.model.TipoIdentificacion;
import pe.edu.upc.spring.service.ITipoIdentificacionService;

@Controller
@RequestMapping("/tipoIdentificacion")
public class TipoIdentificacionController {
	@Autowired
	private ITipoIdentificacionService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; // "bienvenido" es una pagina del frontEnd, pagina de Inicio
	}
	
	@RequestMapping("/")
	public String irPaginaListadoTipoIdentificacion(Map<String, Object> model) {
		model.put("listaTipoIdentificacion", rService.listar());
		return "listTipoIdentificacion"; // "listGestante" es una pagina del frontEnd para listar
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("tipoIdentificacion", new TipoIdentificacion());
		return "insertTipoIdentificacion"; // "race" es una pagina del frontEnd para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute TipoIdentificacion objTipoIdentificacion, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "insertTipoIdentificacion";
		else {
			boolean flag = rService.grabar(objTipoIdentificacion);
			if (flag)
				return "redirect:/tipoIdentificacion/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/insertTipoIdentificacion/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<TipoIdentificacion> objTipoIdentificacion = rService.listarId(id);
		if (objTipoIdentificacion == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/insertTipoIdentificacion/listar";
		}
		else {
			model.addAttribute("tipoIdentificacion",objTipoIdentificacion);
			return "insertTipoIdentificacion";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaTipoIdentificacion", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaTipoIdentificacion", rService.listar());
		}
		return "listTipoIdentificacion";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaTipoIdentificacion", rService.listar());
		return "listTipoIdentificacion";
	}
	
}
