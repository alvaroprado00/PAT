document.addEventListener("DOMContentLoaded", function(event) {

    tokenVerification();

    loadingContents();
});

function tokenVerification() {

    if (typeof Cookies.get('token') === 'undefined') {
        console.log("Cookie not detected");
        console.log(Cookies.get('token'));

        //Si se intenta cargar esta pagina y la cookie del token jwt esta vacia
        //le enviamos de vuelta al index.html
        document.location.href="index.html";
    }

    //Si se encuentra la cookie mostramos por consola el mensaje de la funcion de abajo
    //Además mostramos el html ya que el JS estaba indicado arriba del html
    console.log("Cookie detected");
}

function loadingContents() {
    console.log("No contents to load")
}