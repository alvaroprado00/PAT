package info.jab.microservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			
		//Le indicamos al authManager que para verificar tiene que mirar en el jwtUserDetailsService
		//También le pasamos el tipo de codificador que tiene que usar para las contraseñas
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	//Aqui tenemos el bean que podemos inyectar en otras clases para codificar la contraseña en 
	//base 64
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		//Permitimos el acceso a la base de datos de h2
		//Todos los paths no indicados no se pueden acceder aunque tengas el html definido en resources
		httpSecurity
				.headers()
				.and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/",
						"/index.html", "/home.html", "/out.html",
						"/images/**", "/password.html", "/h2/**","/h2-console",
						"/js/**").permitAll()
				.antMatchers("/api/login").permitAll()
				.anyRequest()  //El resto de request necesitan estar autenticadas
				.authenticated()
				.and()
				// make sure we use stateless session; session won't be used to store user's state.
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		
		// Este filtro previo comprueba que la cabecera esta bien definida y que transporta un token valido
		// En caso contrario no deja pasar absolutamente nada
		// Es por eso que esta todo con permitAll
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	/**
	 * La lógica para definir como punto de entrada el login.html está en el front
	 */
}
