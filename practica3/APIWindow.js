/*
 *	Script para hacer GET de la API REST
 *		
 */


//utilizo una API hecha por mi con datos de usuarios

//si no se especifica el cuerpo del fetch, el verbo es GET

var vectorRespuesta=[]; //aqui guardo toda la info

fetch("https://6032e026a223790017acf78a.mockapi.io/api/v1/Users")

//me suscribo a la promise response
.then(function(response){

	if(response.ok){
		//Si la respuesta es buena te la pasa a formato JSON
		return response.json();
	}else{
		throw response;
	}
})

//me vuelvo a suscribir a otra promesa, en este caso el body de la peticion

.then(function(responseBody){

	//console.log(responseBody);  para comprobar que todo va bien

	vectorRespuesta=responseBody
	console.log(responseBody[0]);
})

//En este punto capturo cualquier error de ambas promises solo con un catch

.catch(function(error){
	console.err(error);
})

var buttonFor1User= document.getElementById("buttonFor1User");
var buttonFor5Users= document.getElementById("buttonFor5Users");
var buttonFor10Users= document.getElementById("buttonFor10Users");


function creadorContenido(numUsuariosAMostrar){
	//Me creo un String con todo lo que voy a ir poniendo

	let contenidoHtml="";

	for(var i=0; i<numUsuariosAMostrar; i++){

		//si quiero meter una variable en una string tiene que ir con ``

		contenidoHtml+="<tr>"+
						`<th scope="row"> Usuario ${i}</th>`+
						`<td>${vectorRespuesta[i].id}</td>`+
						`<td>${vectorRespuesta[i].name}</td>`+
						`<td>${vectorRespuesta[i].userName}</td>`+
						`<td>${vectorRespuesta[i].email}</td>`+
						"</tr>";
	}

	//cuando ya tengo mi String lo meto en mi tabla

	document.getElementById("bodyOfTable").innerHTML="";
	document.getElementById("bodyOfTable").innerHTML=contenidoHtml;
}


buttonFor1User.addEventListener("click", function(event){

	creadorContenido(1);
});

buttonFor5Users.addEventListener("click", function(event){

	creadorContenido(5);
});

buttonFor10Users.addEventListener("click", function(event){

	creadorContenido(10);
});