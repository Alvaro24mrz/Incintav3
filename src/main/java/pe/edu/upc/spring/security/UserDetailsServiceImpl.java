package pe.edu.upc.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.model.Admin;

import pe.edu.upc.spring.repository.IAdminRepository;
import pe.edu.upc.spring.repository.IUsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioRepository uDao;
	private IAdminRepository aDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = uDao.findBynUsuario(username);
		Admin admin = aDao.findBynAdmin(username);
		
		UserBuilder builder = null;
		
		if(usuario != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(usuario.getuPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
			
		}
		//anadi esto
		else if (admin != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(admin.getaPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
			
		}
		
		else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		
		return builder.build();
	}
	

}
