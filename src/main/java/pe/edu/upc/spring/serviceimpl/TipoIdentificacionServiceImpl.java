package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.TipoIdentificacion;
import pe.edu.upc.spring.repository.ITipoIdentificacionRepository;
import pe.edu.upc.spring.service.ITipoIdentificacionService;

@Service
public class TipoIdentificacionServiceImpl implements ITipoIdentificacionService {

	@Autowired
	private ITipoIdentificacionRepository dTipoIdentificacion;
	
	@Override
	@Transactional
	public boolean grabar(TipoIdentificacion tipoIdentificacion) {
		TipoIdentificacion objIdentificacion = dTipoIdentificacion.save(tipoIdentificacion);
		if (objIdentificacion == null)
			return false;
		else
			return true;
	}

	

	@Override
	@Transactional
	public void eliminar(int idTipoIdentificacion) {
		dTipoIdentificacion.deleteById(idTipoIdentificacion);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TipoIdentificacion> listarId(int idTipoIdentificacion) {
		return dTipoIdentificacion.findById(idTipoIdentificacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoIdentificacion> listar() {
		return dTipoIdentificacion.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoIdentificacion> buscarNombre(String nIdentificacion) {
		return dTipoIdentificacion.buscarNombre(nIdentificacion);
	}

}
