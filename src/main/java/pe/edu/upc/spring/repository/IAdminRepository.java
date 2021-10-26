package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer>{
	@Query("from Admin u where u.nAdmin like %:nAdmin%")
	List<Admin> buscarNombre(@Param("nAdmin") String nameUser);
	public Admin findBynAdmin(String nAdmin);

}
