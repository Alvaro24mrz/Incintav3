package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Unidad;

public interface IUnidadService {
	public boolean grabar(Unidad unidad);
	public void eliminar(int idUnidad);
	public Optional<Unidad> listarId(int idUnidad);
	public List<Unidad> listar();
	
	public Optional<Unidad> buscarId(int idUnidad);
	public List<Unidad> buscarNombre(String nUnidad);

}
