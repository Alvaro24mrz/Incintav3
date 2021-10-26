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

import pe.edu.upc.spring.model.PreguntasGestante;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IPreguntasGestanteService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/preguntasGestante")
public class PreguntasGestanteController {
	@Autowired
	private IPreguntasGestanteService pService;
	@Autowired
	private IUsuarioService uService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; 
	}
	
	@RequestMapping("/")
	public String irPaginaListadoPreguntasGestante(Map<String, Object> model) {
		model.put("listaPreguntasGestante", pService.listar());
		return "listPreguntasGestante"; 
	}
	
	

	@RequestMapping("/irRegistrar")
    public String irPaginaRegistrar(Model model) {
        model.addAttribute("listaUsuario", uService.listar());

        model.addAttribute("preguntasGestante", new PreguntasGestante());
        model.addAttribute("usuario", new Usuario());


        return "insertPreguntasGestante"; 
    }
	
	@RequestMapping("/registrar")
    public String registrar(@ModelAttribute PreguntasGestante objPg, BindingResult binRes, Model model) 
        throws ParseException
    {
        if (binRes.hasErrors())
        {
            model.addAttribute("listaUsuario", uService.listar());
            return "insertPreguntasGestante";
        }
        else {
            boolean flag = pService.grabar(objPg);
            if (flag)
                return "redirect:/preguntasGestante/listar";
            else {
                model.addAttribute("mensaje", "ERROR");
                return "redirect:/insertPreguntasGestante/irRegistrar";
            }
        }
    }
	
	
	
	
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException
	{
		Optional<PreguntasGestante> objPg = pService.listarId(id);
		if (objPg == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche, LUZ ROJA");
			return "redirect:/insertPreguntasGestante/listar";
		}
		else {
			model.addAttribute("listaUsuario", uService.listar());
			if(objPg.isPresent())
				objPg.ifPresent(o->model.addAttribute("preguntasGestante",o));
			return "insertPreguntasGestante";
			
			
		}
	}
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model,  @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPreguntasGestante", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaPreguntasGestante", pService.listar());
		}
		return "listPreguntasGestante";
	}
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model ) {
		model.put("listaPreguntasGestante", pService.listar());
		return "listPreguntasGestante";
	}
	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("preguntasGestante", new PreguntasGestante());
		return "searchPreguntasGestante";
	}	

	

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute PreguntasGestante preguntasGestante ) 
	throws ParseException
	{
		
		List<PreguntasGestante> listaPreguntasGestante;
		preguntasGestante.setnTitulo(preguntasGestante.getnTitulo());
		listaPreguntasGestante = pService.buscarNombre(preguntasGestante.getnTitulo());
		
		
		if(listaPreguntasGestante.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaPreguntasGestante", listaPreguntasGestante);
		return "searchPreguntasGestante";
	}	
}
