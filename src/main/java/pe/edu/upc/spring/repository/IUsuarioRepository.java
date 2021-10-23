package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
	@Query("from Usuario u where u.nUsuario like %:nUsuario%")
	List<Usuario> buscarNombre(@Param("nUsuario") String nameUser);
	
	@Query("from Usuario u where u.uApellido like %:uApellido%")
	List<Usuario> buscarApellido(@Param("uApellido") String uApellido);
	
	@Query("from Usuario u where u.numIdentificacion like %:numIdentificacion%")
	List<Usuario> buscarDNI(@Param("numIdentificacion") int numIdentificacion);
	
	public Usuario findBynUsuario(String nUsuario);
}
