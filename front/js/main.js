mapboxgl.accessToken = 'pk.eyJ1IjoiY3Jpc3R5YW5zdiIsImEiOiJjaW80ajZnajUwMWl5djZrcTh0MTM5Y3JzIn0.hyuT6lS1B3q2w-qdXvXXdg';
var map = new mapboxgl.Map({
    container: 'map', // container id
    style: 'mapbox://styles/mapbox/streets-v8', //stylesheet location
    center: [-75.5723, 6.20715], // starting position
    zoom: 13,
    hash: true
});

var id = 0;
map.on('click', function (e) {
    map.addSource("id"+id, {
        "type": "geojson",
        "data": {
            "type": "FeatureCollection",
            "features": [{
                "type": "Feature",
                "geometry": {
                    "type": "Point",
                    "coordinates": [e.lngLat.lng, e.lngLat.lat]
                },
                "properties": {
                    "marker-symbol": "marker"
                }
            }]
        }
    });

    map.addLayer({
        "id": "id"+id,
        "type": "symbol",
        "source": "id"+id,
        "layout": {
            "icon-image": "{marker-symbol}-15",
            "text-field": "{title}",
            "text-font": ["Open Sans Semibold", "Arial Unicode MS Bold"],
            "text-offset": [0, 0.6],
            "text-anchor": "top"
        }
    });
    id++;
});


map.on('load', function () {

});



function a() {
    map.addSource("markers", {
        "type": "geojson",
        "data": {
            "type": "FeatureCollection",
            "features": [{
                "type": "Feature",
                "geometry": {
                    "type": "Point",
                    "coordinates": [-75.56088451843264, 6.208437247241122]
                },
                "properties": {
                    "marker-symbol": "marker"
                }
            }]
        }
    });

    map.addLayer({
        "id": "markers",
        "type": "symbol",
        "source": "markers",
        "layout": {
            "icon-image": "{marker-symbol}-15",
            "text-field": "{title}",
            "text-font": ["Open Sans Semibold", "Arial Unicode MS Bold"],
            "text-offset": [0, 0.6],
            "text-anchor": "top"
        }
    });
}