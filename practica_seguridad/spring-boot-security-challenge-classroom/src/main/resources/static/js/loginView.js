document.addEventListener("DOMContentLoaded", function(event) {

    tokenVerification();

    var form = document.getElementById("myform");
    form.addEventListener("submit", function(e) {
        e.preventDefault();
        return validateForm();
    });
});

function tokenVerification() {

    if (typeof Cookies.get('token') !== 'undefined') {
        console.log("Cookie detected");
        document.location.href="home.html";
    }
}

function validateForm() {
    try {

        //Para pasarle la info al back nos construimos un JSON del tipo {username,password} que vaya en el body de la peticion


        var inputValue1 = document.getElementById("input1").value;
        var inputValue2 = document.getElementById("input2").value;
        const data = { username: inputValue1, password: inputValue2 };
        const address = '/api/login';
        fetch(address, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (typeof data.jwttoken !== 'undefined') {
                    //Si el back nos devuelve un token jwt lo guardamos en las cookies
                    console.log("Authenticated");

                    //Nueva cookie con el token que verificare en el resto de htmls
                    Cookies.set('token', data.jwttoken)

                    //Redirigimos al usuario a home.html
                    document.location.href="/home.html";
                } else {
                    alert("Credential not recognized");
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}