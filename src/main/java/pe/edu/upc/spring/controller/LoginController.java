package pe.edu.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pe.edu.upc.spring.model.MetodoDePago;
import pe.edu.upc.spring.model.Pais;
import pe.edu.upc.spring.model.TipoIdentificacion;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IMetodoDePagoService;
import pe.edu.upc.spring.service.IPaisService;
import pe.edu.upc.spring.service.ITipoIdentificacionService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
public class LoginController {
	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IPaisService pService;
	@Autowired
	private IMetodoDePagoService mpService;
	@Autowired
	private ITipoIdentificacionService tiService;
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		
		return "login";
	}
	
	
	
	@GetMapping("/auth/registroUsuario")
	public String registroForm(Model model) {
		model.addAttribute("listaTipoIdentificacion", tiService.listar());
		model.addAttribute("listaPaises", pService.listar());
		model.addAttribute("listaMDP", mpService.listar());
		
		model.addAttribute("ti", new TipoIdentificacion());
		model.addAttribute("pais", new Pais());
		model.addAttribute("mdp", new MetodoDePago());
		
		model.addAttribute("usuario", new Usuario());

		return "registroUsuario";

	}

	@PostMapping("/auth/registroUsuario")
	public String registroUsuario(@Validated @ModelAttribute Usuario usuario, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("listaTipoIdentificacion", tiService.listar());
			model.addAttribute("listaPaises", pService.listar());
			model.addAttribute("listaMDP", mpService.listar());
			
			return "redirect:/auth/registroUsuario";
		}
		else {
			
			boolean flag = uService.insertar(usuario);
			if (flag)
				return "redirect:/auth/login";
			else
			{
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/auth/registroUsuario";
			}
			
		}
		
	}

}
