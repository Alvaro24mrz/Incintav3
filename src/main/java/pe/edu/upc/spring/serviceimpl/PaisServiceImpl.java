package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Pais;
import pe.edu.upc.spring.repository.IPaisRepository;
import pe.edu.upc.spring.service.IPaisService;

@Service
public class PaisServiceImpl implements IPaisService{
	@Autowired
	private IPaisRepository dPais;
	
	@Override
	@Transactional
	public boolean insertar(Pais p) {
		Pais objPais = dPais.save(p);
		if (objPais == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Pais p) {
		boolean flag = false;
		try {
			dPais.save(p);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un roche, LUZ ROJA");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idPais) {
		dPais.deleteById(idPais);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pais> listarId(int idPais) {
		return dPais.findById(idPais);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pais> listar() {
		return dPais.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pais> buscarNombre(String namePais) {
		return dPais.buscarNombre(namePais);
	}
}
