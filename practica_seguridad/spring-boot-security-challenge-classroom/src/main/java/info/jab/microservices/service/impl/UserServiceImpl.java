package info.jab.microservices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import info.jab.microservices.config.WebSecurityConfig;
import info.jab.microservices.model.UserChangePasswordRequest;
import info.jab.microservices.model.UserDetail;
import info.jab.microservices.repository.UserDetailRepository;
import info.jab.microservices.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired 
	UserDetailRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetail changePassword(UserChangePasswordRequest u) {

		//Una vez llega aqui la peticion se ha comprobado que el token es valido y el user existe
		
		final String newPassword=u.getNewPassword();
		
		//Me cojo el nombre de usuario del contexto de la aplicacion
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		UserDetail newUserDetail = userRepository.getUserDetailByUserName(currentPrincipalName);
		
		//Entonces cambio password y persisto
			
		newUserDetail.setPassword(passwordEncoder.encode(newPassword));
		UserDetail response=userRepository.save(newUserDetail);
		
		
		//Devolvemos el user
		
		return response;
	

	}

	
}
