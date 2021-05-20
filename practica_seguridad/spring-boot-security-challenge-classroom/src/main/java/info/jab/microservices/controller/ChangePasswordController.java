package info.jab.microservices.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.jab.microservices.model.UserChangePasswordRequest;
import info.jab.microservices.model.UserDetail;
import info.jab.microservices.service.UserService;

@RestController
@RequestMapping("/api/changePassword")
public class ChangePasswordController {
	
	@Autowired
	UserService userServiceImpl;
	
	@PostMapping
	public ResponseEntity<UserDetail> changeOfPassword(@RequestBody UserChangePasswordRequest u){
		
		final ResponseEntity<UserDetail> response;
		//Hacemos una comprobación rapida de si las constraseñas nuevas coinciden
	
		//En caso de que no coincidan nos ahorramos todo el flujo
		
		if (u.getNewPassword().equals(u.getNewPassword2())) {
			
			//Todo ha ido bien y me ha cambiado la password		
			 UserDetail userChanged=userServiceImpl.changePassword(u);
		     response = new ResponseEntity<>(userChanged,HttpStatus.OK);
		}else {
			 //Si coinciden las contraseñas mal request
			 response=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return response;
		
		
	}
}
