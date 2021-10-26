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

import pe.edu.upc.spring.model.MetodoDePago;
import pe.edu.upc.spring.service.IMetodoDePagoService;

@Controller
@RequestMapping("/insertarMDP")
public class MetodoDePagoController {

	@Autowired
	private IMetodoDePagoService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; 
	}
	
	@RequestMapping("/")
	public String irPaginaListadoUsuarios(Map<String, Object> model) {
		model.put("listaMDPs", rService.listar());
		return "listMDP"; 
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("mdp", new MetodoDePago());
		return "insertarMDP"; 
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute MetodoDePago objPais, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "insertarMDP";
		else {
			boolean flag = rService.insertar(objPais);
			if (flag)
				return "redirect:/insertarMDP/listar";
			else {
				model.addAttribute("mensaje", "ERROR");
				return "redirect:/insertarMDP/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<MetodoDePago> objMDP = rService.listarId(id);
		if (objMDP == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/insertarMDP/listar"; //CAMBIAR
		}
		else {
			model.addAttribute("mdp",objMDP);
			return "insertarMDP";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaMDPs", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaMDPs", rService.listar());
		}
		return "listMDP";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaMDPs", rService.listar());
		return "listMDP";
	}
	
}
