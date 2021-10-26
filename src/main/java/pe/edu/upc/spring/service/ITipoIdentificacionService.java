package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.TipoIdentificacion;

public interface ITipoIdentificacionService {
	public boolean grabar(TipoIdentificacion tipoIdentificacion);
	
	public void eliminar(int idTipoIdentificacion);
	public Optional<TipoIdentificacion> listarId(int idTipoIdentificacion);
	public List<TipoIdentificacion> listar();
	public List<TipoIdentificacion> buscarNombre(String nIdentificacion);
}
