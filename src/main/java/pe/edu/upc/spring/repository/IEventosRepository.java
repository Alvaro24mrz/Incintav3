package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Eventos;

@Repository
public interface IEventosRepository extends JpaRepository<Eventos, Integer>{
	@Query("from Eventos r where r.tTitulo like %:tTitulo%")
	List<Eventos> buscarNombre(@Param("tTitulo") String tTitulo);
}
