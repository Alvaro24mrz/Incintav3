package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.TipoIdentificacion;

@Repository
public interface ITipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Integer>{
	@Query("from TipoIdentificacion t where t.nIdentificacion like %:nIdentificacion%")
	List<TipoIdentificacion> buscarNombre(@Param("nIdentificacion") String nIdentificacion);
}
