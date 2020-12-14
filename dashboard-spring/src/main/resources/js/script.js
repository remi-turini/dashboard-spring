// Ecoute le chargement de la page
$(document).ready(function () {
    $(".btnInscription").click(function () {
        $(".thirdSection").css("display", "flex");
    });

    $("#openService").click(function () {
        $(".serviceSection").css("display", "flex");
    });

    $(".bi-x").click(function () {
        $(".serviceSection").css("display", "none");
    });

});


