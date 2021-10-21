package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Pais;

public interface IPaisService {
	public boolean insertar(Pais p);
	public boolean modificar(Pais p);
	public void eliminar(int idP);
	public Optional<Pais> listarId(int idP);
	public List<Pais> listar();
	public List<Pais> buscarNombre(String nP);
}
