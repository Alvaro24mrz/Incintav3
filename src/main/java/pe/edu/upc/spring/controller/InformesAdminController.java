package pe.edu.upc.spring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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

import pe.edu.upc.spring.model.MetodoDePago;
import pe.edu.upc.spring.model.Parametro;
import pe.edu.upc.spring.model.Registro;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IMetodoDePagoService;
import pe.edu.upc.spring.service.IParametroService;
import pe.edu.upc.spring.service.IRegistroService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/informeAdmin")
public class InformesAdminController {
	@Autowired
	private IRegistroService rService;
	
	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IParametroService pService;
	
	@Autowired
	private IMetodoDePagoService mpService;

	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; 
	}
	
	@RequestMapping("/mostrar")
    public String getPieChart(Model model) {
        Map<String, Integer> graphData = new TreeMap<>();
        List<Registro> listaRegistros = rService.listar();
        
        List<Integer> valores = new ArrayList<Integer>();
        List<String> fecha = new ArrayList<String>();
        
        for(int i = 0; i<listaRegistros.size();i++) {
        	Registro r = listaRegistros.get(i);
        	valores.add(r.getNumValor());
        	SimpleDateFormat aux = new SimpleDateFormat("yyyy/MM/dd");
        	String aux2 = aux.format(r.getFechaRegistro());
        	fecha.add(aux2);
        }
        
        for(int i = 0; i<listaRegistros.size();i++) {
        	graphData.put(fecha.get(i), valores.get(i));
        }
        
        // MÉTODOS DE PAGO
        
        Map<String, Integer> graphDataMP = new TreeMap<>();
        
        List<MetodoDePago> listaMP = mpService.listar();
        List<Usuario> listaUserMP = uService.listar();
        
        List<Integer> valoresMP = new ArrayList<Integer>();
        List<String> nombreMP = new ArrayList<String>();
        
        for(int i = 0; i<listaMP.size();i++) {
        	MetodoDePago mp = listaMP.get(i);
        	nombreMP.add(mp.getNombreMetodoPago());
        }
        
        for(int i = 0; i<listaMP.size();i++) {
        	MetodoDePago mp = listaMP.get(i);
        	int q = 0;
        	for(int j = 0; j<listaUserMP.size();j++) {
        		Usuario u = listaUserMP.get(i);
        		if(mp.getiDMetodoPago() == u.getiDMetodoPago().getiDMetodoPago()) q++;
        	}
        	valoresMP.add(q);
        	
        }
        
        for(int i = 0; i<listaMP.size();i++) {
        	graphDataMP.put(nombreMP.get(i), valoresMP.get(i));
        }
        
        // MANDAR DATA
        
        model.addAttribute("chartData", graphData); // ---
        model.addAttribute("chartDataMP", graphDataMP); // Método de Pago
        
        
        return "informeAdmin";
    }
	
}