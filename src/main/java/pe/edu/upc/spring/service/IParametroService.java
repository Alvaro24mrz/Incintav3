package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Parametro;

public interface IParametroService {
	public boolean grabar(Parametro parametro);
	public void eliminar(int idParametro);
	public Optional<Parametro> listarId(int idParametro);
	public List<Parametro> listar();
	
	public Optional<Parametro> buscarId(int idParametro);
	public List<Parametro> buscarNombre(String nParametro);
}
