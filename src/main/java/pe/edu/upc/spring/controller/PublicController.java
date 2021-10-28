package pe.edu.upc.spring.controller;


import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IMetodoDePagoService;
import pe.edu.upc.spring.service.IPaisService;
import pe.edu.upc.spring.service.ITipoIdentificacionService;
import pe.edu.upc.spring.service.IUsuarioService;



@Controller
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private IUsuarioService rService;
	@Autowired
	private IPaisService pService;
	@Autowired
	private IMetodoDePagoService mpService;
	@Autowired
	private ITipoIdentificacionService tiService;
	
	
	@RequestMapping("/logout")
	public String index() {
		
		return "logout";
		
	}
	
	@RequestMapping("/bienvenidoInicio")
	public String irPaginaBienvenida() {
		return "bienvenidoInicio"; 
	}
	
	@RequestMapping("/mostrar/{id}")
	public String mostrar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Usuario> objUsuario = rService.listarId(id);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "ERROR");
			return "redirect:/insertarUsuario/bienvenido"; 
		}
		else {
			model.addAttribute("listaTipoIdentificacion", tiService.listar());
			model.addAttribute("listaPaises", pService.listar());
			model.addAttribute("listaMDP", mpService.listar());
			
			if(objUsuario.isPresent())
				objUsuario.ifPresent(o -> model.addAttribute("usuario", o));
			
			return "infoUser";
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Usuario> objUsuario = rService.listarId(id);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "ERROR");
			return "redirect:/insertarUsuario/bienvenido"; 
		}
		else {
			model.addAttribute("listaTipoIdentificacion", tiService.listar());
			model.addAttribute("listaPaises", pService.listar());
			model.addAttribute("listaMDP", mpService.listar());
			
			if(objUsuario.isPresent())
				objUsuario.ifPresent(o -> model.addAttribute("usuario", o));
			
			return "editInfoUser";
		}
	}
	
	@RequestMapping("/guardar")
	public String guardar(@Validated @ModelAttribute Usuario objUsuario, BindingResult binRes, Model model, Map<String, Object> model2) 
		
	{
		if (binRes.hasErrors())
		{	
			model.addAttribute("listaTipoIdentificacion", tiService.listar());
			model.addAttribute("listaPaises", pService.listar());
			model.addAttribute("listaMDP", mpService.listar());
			
			return "redirect:/auth/registrar";
		}
		else 
		{
			boolean flag = rService.modificar(objUsuario);
			if (flag)
				return "redirect:/insertarUsuario/bienvenido";
			else
			{
				model.addAttribute("mensaje", "ERROR");
				return "redirect:/insertarUsuario/bienvenido";
			}
		}
	}
	
	@RequestMapping("/modificarP/{id}")
	public String modificarP(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Usuario> objUsuario = rService.listarId(id);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "ERROR");
			return "redirect:/insertarUsuario/bienvenido"; 
		}
		else {
			model.addAttribute("listaTipoIdentificacion", tiService.listar());
			model.addAttribute("listaPaises", pService.listar());
			model.addAttribute("listaMDP", mpService.listar());
			
			if(objUsuario.isPresent())
				objUsuario.ifPresent(o -> model.addAttribute("usuario", o));
			
			return "editPasswordUser";
		}
	}

}
