package info.jab.microservices.service.impl;

import java.util.List;

import info.jab.microservices.model.UserDetail;
import info.jab.microservices.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 
 * Spring security te obliga a utilizar un service llamado UserDetailsService que incluye el metodo: 
 * 		public UserDetails loadUserByUserName (String userName) throws UsernameNotFoundException
 * 
 * Por tanto, nos creamos un service propio que implemente la interface y definimos el metodo.
 * Como usamos persitencia en BD inyectamos el repository
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailRepository userDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Nuestra clase usuario
		final UserDetail user = userDetailRepository.getUserDetailByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + username);
		}

		//Devolvemos una clase User que es una implementacion de UserDetails que es con la que trabaja Spring security
		return new User(user.getUsername(),
				user.getPassword(),
				List.of((GrantedAuthority) () -> "USER"));
		
		//Constructor tocho
		//User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
		
		//Contructor facil
		//User(String username, String password, Collection<? extends GrantedAuthority> authorities)
	}
}