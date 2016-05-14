var React = require('react');

var http = require('../services/http');


var style = {
    position: 'absolute',
    top: 0,
    bottom: 0,
    width: '100%'
};


var id = 0;
var Map = React.createClass({


    componentDidMount: function () {
        mapboxgl.accessToken = 'pk.eyJ1IjoiY3Jpc3R5YW5zdiIsImEiOiJjaW80ajZnajUwMWl5djZrcTh0MTM5Y3JzIn0.hyuT6lS1B3q2w-qdXvXXdg';
        var map = new mapboxgl.Map({
            container: 'map', // container id
            style: 'mapbox://styles/mapbox/streets-v8', //stylesheet location
            center: [-75.5723, 6.20715], // starting position
            zoom: 13,
            hash: true
        });


        map.on('click', function (e) {
            var match = http.post('/match', {
                lon: e.lngLat.lng,
                lat: e.lngLat.lat
            });

            match.then(function (data) {
                map.addSource("id"+id, {
                    "type": "geojson",
                    "data": {
                        "type": "FeatureCollection",
                        "features": [{
                            "type": "Feature",
                            "geometry": {
                                "type": "Point",
                                "coordinates": [data.loc[0], data.loc[1]]
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
            })

        });


    },
    render: function () {
        return (
            <div style={style} id="map"></div>
        )
    }
});

module.exports = Map;