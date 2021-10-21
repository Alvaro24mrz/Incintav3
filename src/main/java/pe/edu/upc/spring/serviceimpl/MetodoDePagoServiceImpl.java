package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.MetodoDePago;
import pe.edu.upc.spring.repository.IMetodoDePagoRepository;
import pe.edu.upc.spring.service.IMetodoDePagoService;

@Service
public class MetodoDePagoServiceImpl implements IMetodoDePagoService{
	@Autowired
	private IMetodoDePagoRepository dMDP;
	
	@Override
	@Transactional
	public boolean insertar(MetodoDePago mdp) {
		MetodoDePago objMDP = dMDP.save(mdp);
		if (objMDP == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(MetodoDePago mdp) {
		boolean flag = false;
		try {
			dMDP.save(mdp);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un roche, LUZ ROJA");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idMDP) {
		dMDP.deleteById(idMDP);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<MetodoDePago> listarId(int idMDP) {
		return dMDP.findById(idMDP);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MetodoDePago> listar() {
		return dMDP.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<MetodoDePago> buscarNombre(String nameMDP) {
		return dMDP.buscarNombre(nameMDP);
	}
}
