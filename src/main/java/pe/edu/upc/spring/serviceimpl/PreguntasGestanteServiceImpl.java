package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.PreguntasGestante;
import pe.edu.upc.spring.repository.IPreguntasGestanteRepository;
import pe.edu.upc.spring.service.IPreguntasGestanteService;

@Service
public class PreguntasGestanteServiceImpl implements IPreguntasGestanteService {

	@Autowired
	private IPreguntasGestanteRepository dPreguntasGestante;
	
	@Override
	@Transactional
	public boolean grabar(PreguntasGestante preguntasGestante) {
		PreguntasGestante objPg = dPreguntasGestante.save(preguntasGestante);
		if (objPg == null)
			return false;
		else
			return true;
	}

	

	@Override
	@Transactional
	public void eliminar(int idPreguntasGestante) {
		dPreguntasGestante.deleteById(idPreguntasGestante);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PreguntasGestante> listarId(int idPreguntasGestante) {
		return dPreguntasGestante.findById(idPreguntasGestante);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PreguntasGestante> listar() {
		return dPreguntasGestante.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PreguntasGestante> buscarNombre(String nTitulo) {
		return dPreguntasGestante.buscarNombre(nTitulo);
	}

}
