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

import pe.edu.upc.spring.model.Admin;
import pe.edu.upc.spring.service.IAdminService;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAdminService rService;

	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}

	@RequestMapping("/")
	public String irPaginaListadoAdmins(Map<String, Object> model) {
		model.put("listaAdmins", rService.listar());
		return "listAdmins";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("admin", new Admin());
		return "insertAdmin";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Admin objAdmin, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "insertAdmin";
		else {
			boolean flag = rService.insertar(objAdmin);
			if (flag)
				return "redirect:/admin/listar";
			else {
				model.addAttribute("mensaje", "ERROR");
				return "redirect:/admin/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException 
	{
		Optional<Admin> objAdmin = rService.listarId(id);
		if (objAdmin == null) {
			objRedir.addFlashAttribute("mensaje", "ERROR");
			return "redirect:/Admin/listar";
		} 
		else {
			model.addAttribute("admin", objAdmin);
			return "insertAdmin";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				rService.eliminar(id);
				model.put("listaAdmins", rService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaAdmins", rService.listar());
		}
		return "listAdmins";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAdmins", rService.listar());
		return "listAdmins";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Admin usuario) throws ParseException {
		rService.listarId(usuario.getAdminID());
		return "listAdmins";
	}

	@RequestMapping("/irSearch")
	public String irBuscar(Model model) throws ParseException {
		model.addAttribute("usuario", new Admin());
		return "searchAdmin";
	}

	@RequestMapping("/searchAdmin")
	public String buscar(Map<String, Object> model, @ModelAttribute Admin usuario) throws ParseException {
		
		List<Admin> listaAdmins;
		usuario.setnAdmin(usuario.getnAdmin());
		listaAdmins = rService.buscarNombre(usuario.getnAdmin());


		if (listaAdmins.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}

		model.put("listaAdmins", listaAdmins);

		return "searchAdmin";
	}

}