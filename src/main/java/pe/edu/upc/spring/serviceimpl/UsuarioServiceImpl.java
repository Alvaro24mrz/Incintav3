package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.repository.IUsuarioRepository;
import pe.edu.upc.spring.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	@Autowired
	private IUsuarioRepository dUsuario;
	
	//nuevo
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public boolean insertar(Usuario user) {
		//nuevo password:
		user.setuPassword(passwordEncoder.encode(user.getuPassword()));
		Usuario objUser = dUsuario.save(user);
		
		if (objUser == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Usuario user) {
		boolean flag = false;
		try {
			dUsuario.save(user);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un roche, LUZ ROJA");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idUser) {
		dUsuario.deleteById(idUser);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> listarId(int idUser) {
		return dUsuario.findById(idUser);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		return dUsuario.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscarNombre(String nameUser) {
		return dUsuario.buscarNombre(nameUser);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscarApellido(String uApellido) {
		return dUsuario.buscarApellido(uApellido);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscarDNI(int numIdentificacion) {
		return dUsuario.buscarDNI(numIdentificacion);
	}
	
	
	
	//nuevo
	public Usuario findBynUsuario(String nUsuario) {
		return dUsuario.findBynUsuario(nUsuario);
	}

}