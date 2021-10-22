package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Parametro;


@Repository
public interface IParametroRepository  extends JpaRepository<Parametro, Integer>{
	@Query("from Parametro r where r.nParametro like %:nParametro%")
	List<Parametro> buscarNombre(@Param("nParametro") String nameParametro);
	
}
