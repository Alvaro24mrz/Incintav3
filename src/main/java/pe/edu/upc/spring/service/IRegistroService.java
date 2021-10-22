package pe.edu.upc.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Registro;

public interface IRegistroService {
	public boolean grabar(Registro registro);
	public void eliminar(int idRegistro);
	public Optional<Registro> listarId(int idRegistro);
	public List<Registro> listar();
	
	public List<Registro> findByfechaRegistro(Date fechaRegistro);
}
