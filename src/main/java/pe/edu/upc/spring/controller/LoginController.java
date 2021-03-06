package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.annotation.Validated;

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
	
	
	//de usuario controller
	@GetMapping("/auth/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaTipoIdentificacion", tiService.listar());
		model.addAttribute("listaPaises", pService.listar());
		model.addAttribute("listaMDP", mpService.listar());
		
		model.addAttribute("ti", new TipoIdentificacion());
		model.addAttribute("pais", new Pais());
		model.addAttribute("mdp", new MetodoDePago());
		
		model.addAttribute("usuario", new Usuario());
		
		return "insertarUsuario"; 
	}
	
	@PostMapping("/auth/registrar")
	public String registrar(@Validated @ModelAttribute Usuario objUsuario, BindingResult binRes, Model model, Map<String, Object> model2) 
		
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
			objUsuario.setAdmin(2);
			boolean[] v = uService.insertar(objUsuario);
			if ( v[0] == true && v[1] == true && v[2] == true)
				return "redirect:/auth/login";
			else
			{
				model.addAttribute("listaTipoIdentificacion", tiService.listar());
				model.addAttribute("listaPaises", pService.listar());
				model.addAttribute("listaMDP", mpService.listar());
				if ( v[0] == true && v[1] == false && v[2] == false)	model2.put("mensaje", "USERNAME REGISTRADO");
				if ( v[0] == false && v[1] == false && v[2] == false)	model2.put("mensaje", "CORREO REGISTRADO");
				return "insertarUsuario";
			}
		}
	}

}
