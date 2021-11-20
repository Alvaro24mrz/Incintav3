package pe.edu.upc.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImpl userDetailsService; 
	
	//encriptacion de contrasenas
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/public/**","/auth/**","/static/estilos/**","/estilos/**","/public/**", "/imagenes/**", "/static/imagenes/**", "/static/js/**")
		.permitAll()
		.antMatchers("/user/**").access("hasRole('ROLE_USER')")
		.antMatchers( "/insertarPaises/**", "/insertarMDP/**","/tipoIdentificacion/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/insertarUsuario/**", "/private/**","/eventos/**", "/preguntasGestante/**", "/parametro/**", "/registro/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		.anyRequest().authenticated()

		.and()
			.formLogin().loginPage("/public/bienvenidoInicio").defaultSuccessUrl("/private/index",true).failureUrl("/auth/login?error=true")
			.loginProcessingUrl("/auth/login-post").permitAll()
		.and()
			.logout().logoutUrl("/public/logout").logoutSuccessUrl("/public/logout")
		.and().exceptionHandling().accessDeniedPage("/error_403");
		
		
	}

	
}
