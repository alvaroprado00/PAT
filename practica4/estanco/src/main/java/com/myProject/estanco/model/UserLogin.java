package com.myProject.estanco.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase utilizada para manejar los usuarios dentro de la aplicación. Solo tiene dos atributos.
 *
 */

//Al crearme un constructor mediante un User, te desaparece el que te mete por defecto lombok con @Data
//Tienes que volver a indicarselo con @NoArgsConstructor

@Data
@NoArgsConstructor
public class UserLogin {
	
	private String userName;
	private String password;
	
	public UserLogin(User u) {
		this.setUserName(u.getUserName());
		this.setPassword(u.getPassword());
	}
	
}
