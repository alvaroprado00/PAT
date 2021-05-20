package info.jab.microservices.controller;

import info.jab.microservices.model.JwtRequest;
import info.jab.microservices.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import info.jab.microservices.config.JwtTokenUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@PostMapping(value = "/api/login")
	public ResponseEntity<?> login(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
		
	    //Aqui llega la peticion par hacer login y si el Autheticate nos dice que AuthenticationManager encuentra ese usuario
		//y esa contraseña en jwtUserDetailServiceImpl que es donde le hemos dicho que busque en WebConfig
		//Entonces haciendo uso de jwtTokenUtil generamos un nuevo token y se lo pasamos al front

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok().body(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		
		//AuthenticationManager solo tiene un metodo que es autheticate(Authorization a) que verifica que el objeto a existe
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
