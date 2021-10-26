package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.PreguntasGestante;

public interface IPreguntasGestanteService {
	public boolean grabar(PreguntasGestante preguntasGestante);
	
	public void eliminar(int idPreguntasGestante);
	public Optional<PreguntasGestante> listarId(int idPreguntasGestante);
	public List<PreguntasGestante> listar();
	public List<PreguntasGestante> buscarNombre(String nTitulo);
}
