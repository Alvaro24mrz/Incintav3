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
public class UsuarioServiceImpl implements IUsuarioService {
	@Autowired
	private IUsuarioRepository dUsuario;

	// nuevo
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public boolean[] insertar(Usuario user) {

		boolean[] v = new boolean[3];

		List<Usuario> listaUsuarios = dUsuario.buscarCorreo(user.getuCorreo());
		Usuario auxUser = dUsuario.findBynUsuario(user.getnUsuario());

		if (listaUsuarios.isEmpty()) {

			if (auxUser == null) {
				
				// nuevo password:
				user.setuPassword(passwordEncoder.encode(user.getuPassword()));
				Usuario objUser = dUsuario.save(user);
				
				if (objUser == null){ v[0] = true; v[1] = true; v[2] = false; } 
				else { v[0] = true; v[1] = true; v[2] = true; }
				
			} else { v[0] = true; v[1] = false; v[2] = false; }

		} else { v[0] = false; v[1] = false; v[2] = false; }

		return v;
	}

	@Override
	@Transactional
	public boolean modificar(Usuario user) {
		boolean flag = false;
		try {
			user.setuPassword(passwordEncoder.encode(user.getuPassword()));
			dUsuario.save(user);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Ocurrio un error");
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
	public List<Usuario> buscarGestante(String nGestante) {
		return dUsuario.buscarGestante(nGestante);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscarApellido(String uApellido) {
		return dUsuario.buscarApellido(uApellido);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscarDNI(String numIdentificacion) {
		return dUsuario.buscarDNI(numIdentificacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscarCorreo(String uCorreo) {
		return dUsuario.buscarCorreo(uCorreo);
	}

	// nuevo
	public Usuario findBynUsuario(String nUsuario) {
		return dUsuario.findBynUsuario(nUsuario);
	}

}