package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Parametro;
import pe.edu.upc.spring.repository.IParametroRepository;
import pe.edu.upc.spring.service.IParametroService;

@Service
public class ParametroServiceImpl implements IParametroService {

	@Autowired
	private IParametroRepository dParametro;
	
	@Override
	@Transactional
	public boolean grabar(Parametro parametro) {
		Parametro objParametro = dParametro.save(parametro);
		if (objParametro == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idParametro) {
		dParametro.deleteById(idParametro);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Parametro> listarId(int idParametro) {
		return dParametro.findById(idParametro);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Parametro> listar() {
		return dParametro.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Optional<Parametro> buscarId(int idParametro) {
		return dParametro.findById(idParametro);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Parametro> buscarNombre(String nParametro) {
		return dParametro.buscarNombre(nParametro);
	}

}
