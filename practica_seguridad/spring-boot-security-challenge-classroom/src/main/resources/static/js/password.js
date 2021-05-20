document.addEventListener("DOMContentLoaded", function(event){

	//Añadimos un action listener al DOM que se ejecuta cuando ocurre lo siguiente:

	//El evento DOMContentLoaded es disparado cuando 
	//el documento HTML ha sido completamente cargado y parseado, sin esperar hojas de estilo, images y subframes

	//Lo primero que hacemos es verificar si hay token jwt en la cookie de sesion

	checkCookie();

	//Seguimos con nuestras cosas

	const formulario= document.getElementById("form");
	formulario.addEventListener("submit", function(evento){

		//Porsi aunque no tiene comportamiento por defecto
		evento.preventDefault();

		let currentPassword= document.getElementById("currentP").value;
		let newPasswordFirst=document.getElementById("newP").value;
		let newPasswordSecond=document.getElementById("newP2").value;

		if(newPasswordFirst===newPasswordSecond){
		
			changePassword(currentPassword, newPasswordFirst, newPasswordSecond);
		}else{

			alert("No coinciden contraseñas");
		}

	})


})

function checkCookie(){

	var tokenJwt= Cookies.get("token");

	if(typeof(tokenJwt)===undefined){
		document.location.href="index.html"; //redirigimos al index
	}else{
		console.log("HAY COOKIE!");
	}
}

function changePassword(current, newPasswordFirst, newPasswordSecond){

	var jsonToSend={currentPassword:current, newPassword:newPasswordFirst, newPassword2:newPasswordSecond};
	const endPoint="http://localhost:8080/api/changePassword";

	fetch(endPoint, {

		method: "POST",

		body:JSON.stringify(jsonToSend), 

		headers:{

			"Content-type":"application/json",
			"Accept": "application/json",
			"Authorization":"Bearer "+Cookies.get("token")
		}

	})

	.then(function(response){
		if(response.ok){
			return response.json();

		}
	})

	.then(function(r){
		console.log(r);

		alert("CONTRASEÑA CAMBIADA");
		window.location.href="index.html";
	})
	.catch(function(error){console.error(error)})
}	