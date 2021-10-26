package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Admin;
import pe.edu.upc.spring.repository.IAdminRepository;
import pe.edu.upc.spring.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private IAdminRepository dAdmin;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public boolean insertar(Admin user) {
		user.setaPassword(passwordEncoder.encode(user.getaPassword()));
		Admin objUser = dAdmin.save(user);

		if (objUser == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Admin user) {
		boolean flag = false;
		try {
			dAdmin.save(user);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idUser) {
		dAdmin.deleteById(idUser);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Admin> listarId(int idUser) {
		return dAdmin.findById(idUser);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Admin> listar() {
		return dAdmin.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Admin> buscarNombre(String nameUser) {
		return dAdmin.buscarNombre(nameUser);
	}

	// nuevo
	public Admin findBynAdmin(String nAdmin) {
		return dAdmin.findBynAdmin(nAdmin);
	}

}