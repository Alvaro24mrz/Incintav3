package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Unidad;


@Repository
public interface IUnidadRepository  extends JpaRepository<Unidad, Integer>{

	@Query("from Unidad r where r.nUnidad like %:nUnidad%")
	List<Unidad> buscarNombre(@Param("nUnidad") String nUnidad);
}
