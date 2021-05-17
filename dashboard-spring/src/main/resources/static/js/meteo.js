/* ============= */
/* METEO SERVICE */
/* ============= */

// API Key Meteo
const APIKEY = '73826a7e1c072873ce3975420fdbcaf2';
let cityInput;

// Appel à l'API apiMeteo avec ville en paramètre de fonction
let apiMeteo = function(city) {
    // Ville par défaut
    let url = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${APIKEY}&units=metric&lang=fr`;
    cityInput = city;

    // Appel data de l'API
    fetch(url)
        .then( (response) =>
            response.json().then((data) => {
                console.log(data);
                document.querySelector("#city").innerHTML = data.name;
                document.querySelector("#temp").innerHTML = data.main.temp + "°C";
                document.querySelector("#humidity").innerHTML = data.main.humidity + " %";
                document.querySelector("#wind").innerHTML = data.wind.speed + " m/s";
                console.log(response);
            })
        ).catch((err) => console.log("Il y a une erreur : " + err));
}

// Timer
$(document).ready(function () {

    let interval = 5000;

    function refresh() {
        $.ajax( {
            url: "../../templates/authentifier.html",
            cache: false,
            success: function () {
                $("#meteoSection").html();
                console.log("success function");

                setTimeout(function () {
                    refresh();
                    console.log("set time out");
                }, interval);
            }
        });
        apiMeteo(cityInput);
    };

    refresh();
});

