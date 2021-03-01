/*
 *	Script para el submit del formulario
 *		
 */



//validacion con JS (Cambiamos el mensaje que se da al usuario + obligamos a que tenga numeros)

//2 funciones que usan regex y me permiten comprobar cosas que no puedo con etiquetas HTML

function hasNumber(myString) {
  return /\d/.test(myString);
}

function hasSpace (myString) {
   return /\s/.test(myString);
}


var userName= document.getElementById("userName");
var fullName= document.getElementById("name");
var password=document.getElementById("password");
var email=document.getElementById("email");

fullName.addEventListener("input", function(event){
	if((fullName.validity.tooShort)|| (!hasSpace(fullName.value))){
		fullName.setCustomValidity("Indica tu nombre y primer apellido separado de un espacio");
	}else{
		fullName.setCustomValidity("");
	}
});

userName.addEventListener("input", function(event){
	if((userName.validity.tooShort) || (!hasNumber(userName.value))){
		userName.setCustomValidity("El nombre de usuario debe tener mas de 5 caracteres y numeros");
	}else{
		userName.setCustomValidity("");
	}
});

//manejo el submit del form

var formulario=document.getElementById("form");
var newUser={};
var respuesta={};

formulario.addEventListener("submit", function(event){

	//Sin prevenir el default de va toda la info al traste

	event.preventDefault();


	newUser={
		//id:idGenerator.next().value, no hace falta me lo asigna la API
		name:fullName.value,
		userName:userName.value,
		password:password.value,
		email:email.value

	};



	fetch("https://6032e026a223790017acf78a.mockapi.io/api/v1/Users",{
		method: "POST",
		body: JSON.stringify(newUser), //payload info util

		//info extra
		headers:{
			"Content-Type": "application/json", //tipo de datos que mando
  			"Accept": "application/json" //el tipo que recibo
		}
	})

	.then(function(response){
		if(response.ok){
			//El response.ok nos da true si no ha habido fallo de formato
			//si todo bien se devuelve el valor en formato jason
			return response.json();
		}else{
			throw response;
		}
	})

	.then(function(res){

		respuesta=res;
	})

	//Al catch entra si se produce el throw o si hay un error de conexion

	.catch(errorMessage=> console.log(errorMessage));

	//window.location.reload();

	if(hasNumber(respuesta.id)){
		alert("Has sido de alta correctamente");
		formulario.reset();
	}
	
});
