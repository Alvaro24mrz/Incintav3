package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Eventos;
import pe.edu.upc.spring.repository.IEventosRepository;
import pe.edu.upc.spring.service.IEventosService;

@Service
public class EventosServiceImpl implements IEventosService {

	@Autowired
	private IEventosRepository dEventos;
	
	@Override
	@Transactional
	public boolean grabar(Eventos eventos) {
		Eventos objEventos = dEventos.save(eventos);
		if (objEventos == null)
			return false;
		else
			return true;
	}

	//@Override
	//@Transactional
	//public boolean modificar(Race race) {
		//boolean flag = false;
		//try {
			//dRace.save(race);
			//flag = true;
		//}
		//catch(Exception ex) {
			//System.out.println("Ocurrio un roche, LUZ ROJA");
		//}
		//return flag;
	//}

	@Override
	@Transactional
	public void eliminar(int idEventos) {
		dEventos.deleteById(idEventos);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Eventos> listarId(int idEventos) {
		return dEventos.findById(idEventos);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Eventos> listar() {
		return dEventos.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Eventos> buscarNombre(String tTitulo) {
		return dEventos.buscarNombre(tTitulo);
	}

}
