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
		http.authorizeRequests().antMatchers("/public/**","/auth/**","/css/**","/public/**", "/imagenes/**", "/static/imagenes/**").permitAll().anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/public/bienvenidoInicio").defaultSuccessUrl("/private/index",true).failureUrl("/auth/login?error=true")
			.loginProcessingUrl("/auth/login-post").permitAll()
		.and()
			.logout().logoutUrl("/public/logout").logoutSuccessUrl("/public/logout");
			//			.logout(logout ->logout.invalidateHttpSession(true).logoutUrl("/public/logout").logoutSuccessUrl("/public/bienvenidoInicio"));

		
	}

	
}
