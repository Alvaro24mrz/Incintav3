package pe.edu.upc.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/private")
public class PrivateController {

	@Autowired
	private IUsuarioService uService;

	private String clientName = "default";

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String value) {
		clientName = value;
	}

	@RequestMapping("/index")
	public String index(Authentication auth, HttpSession session) {

		String username = auth.getName();

		if (session.getAttribute("usuario") == null) {
			Usuario usuario = uService.findBynUsuario(username);
			usuario.setuPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		clientName = username;

		return "index";

	}

}
