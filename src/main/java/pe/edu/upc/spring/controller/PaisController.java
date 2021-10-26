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

import pe.edu.upc.spring.model.Pais;
import pe.edu.upc.spring.service.IPaisService;

@Controller
@RequestMapping("/insertarPaises")
public class PaisController {

	@Autowired
	private IPaisService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; 
	}
	
	@RequestMapping("/")
	public String irPaginaListadoUsuarios(Map<String, Object> model) {
		model.put("listaPaises", rService.listar());
		return "listPais"; 
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("pais", new Pais());
		return "insertarPaises"; 
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Pais objPais, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
			return "insertarPaises";
		else {
			boolean flag = rService.insertar(objPais);
			if (flag)
				return "redirect:/insertarPaises/listar";
			else {
				model.addAttribute("mensaje", "ERROR");
				return "redirect:/insertarPaises/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Pais> objPais = rService.listarId(id);
		if (objPais == null) {
			objRedir.addFlashAttribute("mensaje", "ERROR");
			return "redirect:/insertarPaises/listar"; //CAMBIAR
		}
		else {
			model.addAttribute("pais",objPais);
			return "insertarPaises";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaPaises", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaPaises", rService.listar());
		}
		return "listPais";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaPaises", rService.listar());
		return "listPais";
	}
	
}
