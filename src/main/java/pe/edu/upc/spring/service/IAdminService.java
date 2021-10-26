package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Admin;

public interface IAdminService {
	public boolean insertar(Admin user);
	public boolean modificar(Admin user);
	public void eliminar(int idRace);
	public Optional<Admin> listarId(int idUser);
	public List<Admin> listar();
	public List<Admin> buscarNombre(String nUser);

	
	public Admin findBynAdmin(String nAdmin);

}
