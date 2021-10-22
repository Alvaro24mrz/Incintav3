package pe.edu.upc.spring.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Registro;
import pe.edu.upc.spring.repository.IRegistroRepository;
import pe.edu.upc.spring.service.IRegistroService;

@Service
public class RegistroServiceImpl implements IRegistroService {

	@Autowired
	private IRegistroRepository dRegistro;
	
	@Override
	@Transactional
	public boolean grabar(Registro registro) {
		Registro objRegistro = dRegistro.save(registro);
		if (objRegistro == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idRegistro) {
		dRegistro.deleteById(idRegistro);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Registro> listarId(int idRegistro) {
		return dRegistro.findById(idRegistro);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Registro> listar() {
		return dRegistro.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Registro> findByfechaRegistro(Date fechaRegistro){
		return dRegistro.findByfechaRegistro(fechaRegistro);
		
	}

	
	
	


}
