package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.MetodoDePago;

public interface IMetodoDePagoService {
	public boolean insertar(MetodoDePago mdp);
	public boolean modificar(MetodoDePago mdp);
	public void eliminar(int idMDP);
	public Optional<MetodoDePago> listarId(int idMDP);
	public List<MetodoDePago> listar();
	public List<MetodoDePago> buscarNombre(String nMDP);
}
