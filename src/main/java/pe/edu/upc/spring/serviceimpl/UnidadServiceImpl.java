package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Unidad;
import pe.edu.upc.spring.repository.IUnidadRepository;
import pe.edu.upc.spring.service.IUnidadService;

@Service
public class UnidadServiceImpl implements IUnidadService {

	@Autowired
	private IUnidadRepository dUnidad;
	
	@Override
	@Transactional
	public boolean grabar(Unidad unidad) {
		Unidad objUnidad = dUnidad.save(unidad);
		if (objUnidad == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idUnidad) {
		dUnidad.deleteById(idUnidad);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Unidad> listarId(int idUnidad) {
		return dUnidad.findById(idUnidad);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Unidad> listar() {
		return dUnidad.findAll();
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Unidad> buscarId(int idUnidad) {
		return dUnidad.findById(idUnidad);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Unidad> buscarNombre(String nUnidad) {
		return dUnidad.buscarNombre(nUnidad);
	}

	

}
