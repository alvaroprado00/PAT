package com.myProject.estanco.model;

import lombok.Data;
import lombok.NoArgsConstructor;


//utilizo la anotacion Data que crea los getters, setters y constructor por defecto

@Data
@NoArgsConstructor
public class User {
	
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;

	//Al definirnos un constructor se va el que mete por defecto lombok
	//Hay que indicarle explicitamente que te cree uno con @NoArgsConstructor
	public User(UserLogin userLogin) {
		
		//constructor para crearme un User a partir de la clase intermedia UserLogin
		this.setFirstName("demo");
		this.setLastName("demo");
		this.setPassword(userLogin.getPassword());
		this.setUserName(userLogin.getUserName());
		this.setEmail("loquesea@gmail.com");
	
	}
	
	public boolean equalsTo(Object o, String type) {
		
		type= type.toLowerCase();
		boolean response;
		
		response=false;
		
		if(o instanceof User) {
			
			User u= (User)o;
			
			//Para evitar caseSensitive behaviours
			
			String userNameFromU=u.getUserName().toLowerCase();
			String passwordFromU=u.getPassword().toLowerCase();
			
			
			if(type.equals("strict")) {
				
				if(userNameFromU.equals(this.getUserName().toLowerCase()) && passwordFromU.equals(this.getPassword().toLowerCase())) {
					
					response=true;
				}
			}else if(type.equals("relaxed")) {
				
				if(userNameFromU.equals(this.getUserName().toLowerCase())) {
					response=true;
				}
			}
			
		}
		
		return response;
	}
	
}
