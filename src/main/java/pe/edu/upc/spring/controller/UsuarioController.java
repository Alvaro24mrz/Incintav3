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

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.model.Pais;
import pe.edu.upc.spring.model.MetodoDePago;

import pe.edu.upc.spring.service.IUsuarioService;
import pe.edu.upc.spring.service.IPaisService;
import pe.edu.upc.spring.service.IMetodoDePagoService;

@Controller
@RequestMapping("/insertarUsuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService rService;
	@Autowired
	private IPaisService pService;
	@Autowired
	private IMetodoDePagoService mpService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; 
	}
	
	@RequestMapping("/")
	public String irPaginaListadoUsuarios(Map<String, Object> model) {
		model.put("listaUsuarios", rService.listar());
		return "listUsuarios"; 
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaPaises", pService.listar());
		model.addAttribute("listaMDP", mpService.listar());
		
		model.addAttribute("pais", new Pais());
		model.addAttribute("mdp", new MetodoDePago());
		
		model.addAttribute("usuario", new Usuario());
		
		return "insertarUsuario"; 
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Usuario objUsuario, BindingResult binRes, Model model) 
		throws ParseException
	{
		if (binRes.hasErrors())
		{		
			model.addAttribute("listaPaises", pService.listar());
			model.addAttribute("listaMDP", mpService.listar());
			
			return "insertarUsuario";
		}
		else 
		{
			boolean flag = rService.insertar(objUsuario);
			if (flag)
				return "redirect:/insertarUsuario/listar";
			else
			{
				model.addAttribute("mensaje", "Ocurrio un rochezaso, LUZ ROJA");
				return "redirect:/insertarUsuario/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Usuario> objUsuario = rService.listarId(id);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/insertarUsuario/listar"; //CAMBIAR
		}
		else {
			model.addAttribute("listaPaises", pService.listar());
			model.addAttribute("listaMDP", mpService.listar());
			
			if(objUsuario.isPresent())
				objUsuario.ifPresent(o -> model.addAttribute("usuario", o));
			
			return "insertarUsuario";
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaUsuarios", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaUsuarios", rService.listar());
		}
		return "listUsuarios";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaUsuarios", rService.listar());
		return "listUsuarios";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Usuario usuario) 
	throws ParseException
	{
		rService.listarId(usuario.getUsuarioID());
		return "listUsuarios";
	}
	
	@RequestMapping("/irBucar")
	public String irBuscar(Model model) 
	throws ParseException
	{
		model.addAttribute("usuario", new Usuario());
		return "buscar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Usuario usuario) 
	throws ParseException
	{
		List<Usuario> listaUsuarios;
		usuario.setnUsuario(usuario.getnUsuario());
		listaUsuarios = rService.buscarNombre(usuario.getnUsuario());
		
		if(listaUsuarios.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaUsuarios", listaUsuarios);
		
		return "buscar";
	}
	
}