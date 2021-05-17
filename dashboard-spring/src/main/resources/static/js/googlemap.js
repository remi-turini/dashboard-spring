let map;

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 43.3, lng: 5.4 },
        zoom: 8,
    });
}