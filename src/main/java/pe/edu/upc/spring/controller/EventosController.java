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

import pe.edu.upc.spring.model.Eventos;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IEventosService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	@Autowired
	private IEventosService rService;
	@Autowired
	private IUsuarioService uService;

	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //
	}

	@RequestMapping("/")
	public String irPaginaListadoEventos(Map<String, Object> model) {
		model.put("listaEventos", rService.listar());
		return "listEventos";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {

		model.addAttribute("listaUsuarios", uService.listar());

		model.addAttribute("usuario", new Usuario());
		model.addAttribute("eventos", new Eventos());
		return "insertEventos";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Eventos objEventos, BindingResult binRes, Model model, Map<String, Object> model2)
			throws ParseException {
		if (binRes.hasErrors()) {

			model.addAttribute("listaUsuarios", uService.listar());
			return "insertEventos";
		} else {

			if (objEventos.gethInicio().compareTo(objEventos.gethFin()) < 0) {
				boolean flag = rService.grabar(objEventos);
				if (flag)
					return "redirect:/eventos/listar";
				else {
					model.addAttribute("mensaje", "ERROR");
					return "redirect:/insertEventos/irRegistrar";
				}
			} else {
				model2.put("mensaje", "Los eventos no terminan antes de comenzar.");
				return "insertEventos";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Eventos> objEventos = rService.listarId(id);
		if (objEventos == null) {
			objRedir.addFlashAttribute("mensaje", "ERROR");
			return "redirect:/insertEventos/listar";
		} else {
			model.addAttribute("listaUsuarios", uService.listar());
			if (objEventos.isPresent())
				objEventos.ifPresent(o -> model.addAttribute("eventos", o));
			return "insertEventos";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				rService.eliminar(id);
				model.put("listaEventos", rService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaEventos", rService.listar());
		}
		return "listEventos";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEventos", rService.listar());
		return "listEventos";
	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("eventos", new Eventos());
		return "searchEventos";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Eventos eventos) throws ParseException {

		List<Eventos> listaEventos;
		eventos.setFechaEvento(eventos.getFechaEvento());
		listaEventos = rService.findByFechaEvento(eventos.getFechaEvento());

		if (listaEventos.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}

		model.put("listaEventos", listaEventos);
		return "searchEventos";
	}

}
