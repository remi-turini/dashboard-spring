// Ecoute le chargement de la page
$(document).ready(function () {

    /* Ajouter des services */
    // Open
    $("#openService").click(function () {
        $(".serviceSection").css("display", "flex");
    });
    //---

    // Fenêtre service de la plateforme
    $(".bi-plus-circle-map").click(function () {
        $(".mapSection").css("display", "flex");
    });

    $(".bi-plus-circle-meteo").click(function () {
        $("#meteoSection").css("display", "flex");
    });

    $(".bi-plus-circle-unsplash").click(function () {
        $("#unsplashSection").css("display", "flex");
    });
    // ---

    // Close
    $(".bi-x-square").click(function () {
        $(".serviceSection").css("display", "none");
    });
    //---
    /* ===== */

    /* Fermer un ou plusieurs services */
    $(".closedServiceMap").click(function () {
        $(".mapSection").css("display", "none");
    });

    $(".closedServiceMeteo").click(function () {
        $("#meteoSection").css("display", "none");
    });

    $(".closedServiceUnsplash").click(function () {
        $("#unsplashSection").css("display", "none");
    });
    /* ===== */


});




