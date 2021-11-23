 package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.MetodoDePago;
import pe.edu.upc.spring.model.Pais;
import pe.edu.upc.spring.model.TipoIdentificacion;
import pe.edu.upc.spring.model.Usuario;

import pe.edu.upc.spring.service.IUsuarioService;
import pe.edu.upc.spring.service.IPaisService;
import pe.edu.upc.spring.service.IMetodoDePagoService;
import pe.edu.upc.spring.service.ITipoIdentificacionService;

@Controller
@RequestMapping("/insertarUsuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService rService;
	@Autowired
	private IPaisService pService;
	@Autowired
	private IMetodoDePagoService mpService;
	@Autowired
	private ITipoIdentificacionService tiService;
	
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
		
		model.addAttribute("listaTipoIdentificacion", tiService.listar());
		model.addAttribute("listaPaises", pService.listar());
		model.addAttribute("listaMDP", mpService.listar());
		
		model.addAttribute("ti", new TipoIdentificacion());
		model.addAttribute("pais", new Pais());
		model.addAttribute("mdp", new MetodoDePago());
		
		model.addAttribute("usuario", new Usuario());
		
		return "insertarUsuarioAdmin"; 
	}
	
	@RequestMapping("/registrar")
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
			boolean[] v = rService.insertar(objUsuario);
			if ( v[0] == true && v[1] == true && v[2] == false)
				return "redirect:/insertarUsuario/listar";
			else
			{
				model.addAttribute("listaTipoIdentificacion", tiService.listar());
				model.addAttribute("listaPaises", pService.listar());
				model.addAttribute("listaMDP", mpService.listar());
				if ( v[0] == false && v[1] == false && v[2] == false)	model2.put("mensaje", "CORREO REGISTRADO");
				if ( v[0] == true && v[1] == false && v[2] == false)	model2.put("mensaje", "USERNAME REGISTRADO");
				return "insertarUsuarioAdmin";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<Usuario> objUsuario = rService.listarId(id);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "ERROR");
			return "redirect:/insertarUsuarioAdmin/listar"; 
		}
		else {
			model.addAttribute("listaTipoIdentificacion", tiService.listar());
			model.addAttribute("listaPaises", pService.listar());
			model.addAttribute("listaMDP", mpService.listar());
			
			if(objUsuario.isPresent())
				objUsuario.ifPresent(o -> model.addAttribute("usuario", o));
			
			return "modificarUsuarioAdmin";
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
				return "redirect:/insertarUsuario/listar";
			else
			{
				model.addAttribute("mensaje", "ERROR");
				return "redirect:/insertarUsuario/listar";
			}
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
	
	@RequestMapping("/irSearch")
	public String irBuscar(Model model) 
	throws ParseException
	{
		model.addAttribute("usuario", new Usuario());
		return "searchUsuario";
	}
	
	@RequestMapping("/searchUsuario")
	public String buscar(Map<String, Object> model, @ModelAttribute Usuario usuario) 
	throws ParseException
	{
		List<Usuario> listaUsuarios;
		usuario.setnUsuario(usuario.getnUsuario());
		listaUsuarios = rService.buscarNombre(usuario.getnUsuario());
		
		if(listaUsuarios.isEmpty()) {
			listaUsuarios = rService.buscarApellido(usuario.getnUsuario());
		}
		
		if(listaUsuarios.isEmpty()) {
			listaUsuarios = rService.buscarDNI(usuario.getnUsuario());
		}
		
		if(listaUsuarios.isEmpty()) {
			listaUsuarios = rService.buscarCorreo(usuario.getnUsuario());
		}
		if(listaUsuarios.isEmpty()) {
			listaUsuarios = rService.buscarGestante(usuario.getnUsuario());
		}
		if(listaUsuarios.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaUsuarios", listaUsuarios);
		
		return "searchUsuario";
	}
	
	@PostMapping("/show")
	public String show(Usuario u) {
		return "listUsuarios";
	}
	
}