package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.MetodoDePago;

@Repository
public interface IMetodoDePagoRepository extends JpaRepository<MetodoDePago, Integer>{
	@Query("from MetodoDePago r where r.nombreMetodoPago like %:nombreMetodoPago%")
	List<MetodoDePago> buscarNombre(@Param("nombreMetodoPago") String nameMetodoDePago);
}
