package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Usuario;

public interface IUsuarioService {
	public boolean insertar(Usuario user);
	public boolean modificar(Usuario user);
	public void eliminar(int idRace);
	public Optional<Usuario> listarId(int idUser);
	public List<Usuario> listar();
	public List<Usuario> buscarNombre(String nUser);
	public List<Usuario> buscarApellido(String uApellido);
	public List<Usuario> buscarDNI(String numIdentificacion);
	public List<Usuario> buscarCorreo(String uCorreo);
	public List<Usuario> buscarGestante(String nGestante);
	
	
	public Usuario findBynUsuario(String nUsuario);
}
