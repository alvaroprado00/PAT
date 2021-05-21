# Examen PAT 2021 / Parte práctica

## Introduccion

La estación de Ski se esta preparando para la nueva campaña
de invierno 2021 y para aumentar sus ingresos, ha preparado
un nuevo servicio de alquiler de Refugios. Para ello,
se ha desarrollado un nuevo sitio web.

## Preguntas

- [ ] 1 Desarrolla una feature completa que permita visualizar en el documento Web (`index.html`) la temperatura media del
  último año. Desarrolla la funcionalidad end to end: `html, javascript, REST controller, service, repository y entity`. (6.5 Puntos)
- [ ] 2 Dado el desarrollo `Subscribe` implementa al menos 4 Unit Tests que cubran posibles Requests `SubscribeRequest` (1 Punto)
- [ ] 3 Dada el area funcional de reservas, implementa en un nuevo controllador `ReservasController` 
los siguientes endpoints: (1.5 Puntos)
  - Mostrar todas las reservas actuales
  - Mostrar detalle de una reserva X
  - Borrar una reserva X
  
**Notas:**

- En cada pregunta, añade un comentario en cada elemento que añadas explicando su uso.
- En la pregunta 2 apoyate en las ideas `The Right-BICEP` para desarrollar los Tests sobre la clase `SubscribeRequest`  
  y organiza tus tests con la estructura `//given //when //then` 
- En la pregunta 3, puedes apoyarte en los datos cargados en la base de datos para devolver los datos.
  Revisa que los endpoints empleen el metodo HTTP mas adecuado y una semántica de paths acorde al estándar RESTful.
- Revisa que tu proyecto pasa todos los tests con `mvn clean test`  



    
