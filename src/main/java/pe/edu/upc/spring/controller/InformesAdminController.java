package pe.edu.upc.spring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.model.Eventos;
import pe.edu.upc.spring.model.MetodoDePago;
import pe.edu.upc.spring.model.Pais;
import pe.edu.upc.spring.model.Parametro;
import pe.edu.upc.spring.model.PreguntasGestante;
import pe.edu.upc.spring.model.Registro;
import pe.edu.upc.spring.model.TipoIdentificacion;
import pe.edu.upc.spring.model.Usuario;

import pe.edu.upc.spring.service.IEventosService;
import pe.edu.upc.spring.service.IMetodoDePagoService;
import pe.edu.upc.spring.service.IPaisService;
import pe.edu.upc.spring.service.IParametroService;
import pe.edu.upc.spring.service.IPreguntasGestanteService;
import pe.edu.upc.spring.service.IRegistroService;
import pe.edu.upc.spring.service.ITipoIdentificacionService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/informeAdmin")
public class InformesAdminController {
	@Autowired
	private IRegistroService reService;
	
	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IParametroService prmService;
	
	@Autowired
	private IMetodoDePagoService mpService;
	
	@Autowired
	private ITipoIdentificacionService tiService;
	
	@Autowired
	private IPaisService paisService;
	
	@Autowired
	private IPreguntasGestanteService pgService;
	
	@Autowired
	private IEventosService evService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; 
	}
	
	@RequestMapping("/mostrar")
    public String getPieChart(Model model) {
		
		// HISTORIAL DE REGISTRO
		
        Map<String, Integer> graphData = new TreeMap<>();
        List<Registro> listaRegistros = reService.listar();
        
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
        		Usuario u = listaUserMP.get(j);
        		if(mp.getiDMetodoPago() == u.getiDMetodoPago().getiDMetodoPago()) q++;
        	}
        	valoresMP.add(q);
        }
        
        for(int i = 0; i<listaMP.size();i++) {
        	graphDataMP.put(nombreMP.get(i), valoresMP.get(i));
        }
        
        // TIPO DE IDENTIFICACIÓN
        
        Map<String, Integer> graphDataTI = new TreeMap<>();
        
        List<TipoIdentificacion> listaTI = tiService.listar();
        
        List<Integer> valoresTI = new ArrayList<Integer>();
        List<String> nombreTI = new ArrayList<String>();
        
        for(int i = 0; i<listaTI.size();i++) {
        	TipoIdentificacion ti = listaTI.get(i);
        	nombreTI.add(ti.getnIdentificacion());
        }
        
        for(int i = 0; i<listaTI.size();i++) {
        	TipoIdentificacion ti = listaTI.get(i);
        	int q = 0;
        	for(int j = 0; j<listaUserMP.size();j++) {
        		Usuario u = listaUserMP.get(j);
        		if(ti.getIdTipoIdentificacion() == u.getiDTipoIdentificacion().getIdTipoIdentificacion()) q++;
        	}
        	valoresTI.add(q);
        }
        
        for(int i = 0; i<listaTI.size();i++) {
        	graphDataTI.put(nombreTI.get(i), valoresTI.get(i));
        }
        
        // PAIS
        
        Map<String, Integer> graphDataPAIS = new TreeMap<>();
        
        List<Pais> listaPais = paisService.listar();
        
        List<Integer> valoresPais = new ArrayList<Integer>();
        List<String> nombrePais = new ArrayList<String>();
        
        for(int i = 0; i<listaPais.size();i++) {
        	Pais pais = listaPais.get(i);
        	nombrePais.add(pais.getNombrePais());
        }
        
        for(int i = 0; i<listaPais.size();i++) {
        	Pais pais = listaPais.get(i);
        	int q = 0;
        	for(int j = 0; j<listaUserMP.size();j++) {
        		Usuario u = listaUserMP.get(j);
        		if(pais.getIdPais() == u.getiDPais().getIdPais()) q++;
        	}
        	valoresPais.add(q);
        }
        
        for(int i = 0; i<listaPais.size();i++) {
        	graphDataPAIS.put(nombrePais.get(i), valoresPais.get(i));
        }
        
        // PREGUNTAS GESTANTE
        
        Map<String, Integer> graphDataPG = new TreeMap<>();
        List<PreguntasGestante> listaPG = pgService.listar();
        
        List<Integer> valoresPG = new ArrayList<Integer>();
        List<String> fechaPG = new ArrayList<String>();
        
        for(int i = 0; i<listaPG.size();i++) {
        	PreguntasGestante pg = listaPG.get(i);
        	SimpleDateFormat aux = new SimpleDateFormat("yyyy/MM/dd");
        	String aux1 = aux.format(pg.getFecha());
        	fechaPG.add(aux1);
        }
        
        for(int i = 0; i<fechaPG.size();i++) {
        	int q = 0;
        	
        	for(int j = 0; j<listaPG.size();j++) {
        		PreguntasGestante pg = listaPG.get(j);
        		SimpleDateFormat aux1 = new SimpleDateFormat("yyyy/MM/dd");
            	String aux2 = aux1.format(pg.getFecha());

        		if(fechaPG.get(i).equals(aux2)) q++;
        	}
        	valoresPG.add(q);
        }
        
        for(int i = 0; i<listaPG.size();i++) {
        	graphDataPG.put(fechaPG.get(i), valoresPG.get(i));
        }
        
        // EVENTOS
		
        Map<String, Integer> graphDataEV = new TreeMap<>();
        List<Eventos> listaEV = evService.listar();
        
        List<Integer> valoresEV = new ArrayList<Integer>();
        List<String> fechaEV = new ArrayList<String>();
        
        for(int i = 0; i<listaEV.size();i++) {
        	Eventos ev = listaEV.get(i);
        	SimpleDateFormat aux = new SimpleDateFormat("yyyy/MM/dd");
        	String aux1 = aux.format(ev.getFechaEvento());
        	fechaEV.add(aux1);
        }
        
        for(int i = 0; i<fechaEV.size();i++) {
        	int p = 0;
        	
        	for(int j = 0; j<listaEV.size();j++) {
        		Eventos ev = listaEV.get(j);
        		SimpleDateFormat aux1 = new SimpleDateFormat("yyyy/MM/dd");
            	String aux2 = aux1.format(ev.getFechaEvento());
            	
        		if(fechaEV.get(i).equals(aux2)) p++;
        	}
        	valoresEV.add(p);
        }
        
        for(int i = 0; i<listaEV.size();i++) {
        	graphDataEV.put(fechaEV.get(i), valoresEV.get(i));
        }
        
        // REGISTROS
        
        Map<String, Integer> graphDataRE = new TreeMap<>();
        List<Registro> listarRE = reService.listar();
        
        List<Integer> valoresRE = new ArrayList<Integer>();
        List<String> fechaRE = new ArrayList<String>();
        
        for(int i = 0; i<listarRE.size();i++) {
        	Registro re = listarRE.get(i);
        	SimpleDateFormat aux = new SimpleDateFormat("yyyy/MM/dd");
        	String aux1 = aux.format(re.getFechaRegistro());
        	fechaRE.add(aux1);
        }
        
        for(int i = 0; i<fechaRE.size();i++) {
        	int p = 0;
        	
        	for(int j = 0; j<listarRE.size();j++) {
        		Registro re = listarRE.get(j);
        		SimpleDateFormat aux1 = new SimpleDateFormat("yyyy/MM/dd");
            	String aux2 = aux1.format(re.getFechaRegistro());
            	
        		if(fechaRE.get(i).equals(aux2)) p++;
        	}
        	valoresRE.add(p);
        }
        
        for(int i = 0; i<listarRE.size();i++) {
        	graphDataRE.put(fechaRE.get(i), valoresRE.get(i));
        }
        
        
        // PARÁMETRO
        
        Map<String, Integer> graphDataPRM = new TreeMap<>();
        
        List<Parametro> listaPRM = prmService.listar();
        
        List<Integer> valoresPRM = new ArrayList<Integer>();
        List<String> nombrePRM = new ArrayList<String>();
        
        for(int i = 0; i<listaPRM.size();i++) {
        	Parametro prm = listaPRM.get(i);
        	nombrePRM.add(prm.getnParametro());
        }
        
        for(int i = 0; i<listaPRM.size();i++) {
        	Parametro prm = listaPRM.get(i);
        	int q = 0;
        	for(int j = 0; j<listarRE.size();j++) {
        		Registro r = listarRE.get(j);
        		if(prm.getIdParametro() == r.getParametro().getIdParametro()) q++;
        	}
        	valoresPRM.add(q);
        }
        
        for(int i = 0; i<listaPRM.size();i++) {
        	graphDataPRM.put(nombrePRM.get(i), valoresPRM.get(i));
        }
        
        // MANDAR DATA
        
        model.addAttribute("chartData", graphData); // ---
        model.addAttribute("chartDataMP", graphDataMP); // Método de Pago
        model.addAttribute("chartDataTI", graphDataTI); // Tipo de Identificación
        model.addAttribute("chartDataPAIS", graphDataPAIS); // País
        model.addAttribute("chartDataPG", graphDataPG);  // Preguntas Gestante
        model.addAttribute("chartDataEV", graphDataEV);  // Eventos
        model.addAttribute("chartDataRE", graphDataRE);  // Registro
        model.addAttribute("chartDataPRM", graphDataPRM);  // Registro
        
        return "informeAdmin";
    }
	
}