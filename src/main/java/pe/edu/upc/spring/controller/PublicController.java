package pe.edu.upc.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/public")
public class PublicController {
	
	@RequestMapping("/logout")
	public String index() {
		
		return "logout";
		
	}
	
	@RequestMapping("/bienvenidoInicio")
	public String irPaginaBienvenida() {
		return "bienvenidoInicio"; 
	}

}
