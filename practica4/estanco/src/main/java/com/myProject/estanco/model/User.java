package com.myProject.estanco.model;

import lombok.Data;


//utilizo la anotacion Data que crea los getters, setters y constructor por defecto
@Data
public class User {
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	
	
	public boolean equalsTo(Object o) {
		
		if(o instanceof User) {
			
			User u= (User)o;
			
			userName=u.getUserName().toLowerCase();
			password=u.getPassword().toLowerCase();
			
			//Para evitar caseSensitive behaviours
			
			if(userName.equals(this.getUserName().toLowerCase()) && u.password.equals(this.getPassword().toLowerCase())) {
				return true;
			}else {
				return false;
			}
			
		}else {
			
			return false;
		}
	}
	
}
