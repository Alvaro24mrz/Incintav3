package pe.edu.upc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Eventos;

@Repository
public interface IEventosRepository extends JpaRepository<Eventos, Integer>{
	List<Eventos> findByFechaEvento(Date fechaEvento);
}
