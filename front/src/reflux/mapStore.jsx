var Reflux = require('reflux');

var MapActions = require('./mapActions.jsx');
var http = require('../services/http');

var MapStore = Reflux.createStore({
    listenables: [MapActions],
    init: function () {

        this.listDir = [];

        var MapboxClient = require('mapbox');
        this.client = new MapboxClient('pk.eyJ1IjoiY3Jpc3R5YW5zdiIsImEiOiJjaW80ajZnajUwMWl5djZrcTh0MTM5Y3JzIn0.hyuT6lS1B3q2w-qdXvXXdg');

        //
    },
    addPoint: function (e) {

            console.log(e);

            var match = http.post('/match', {
                lon: e.lngLat.lng,
                lat: e.lngLat.lat
            });

            match.then(function (data) {
                if (data.status) {

                    this.client.geocodeReverse(
                        { latitude: data.data.loc[1], longitude: data.data.loc[0] },
                        function(err, res) {

                            console.log(res);
                            var tex = res.features[0].place_name;
                            data.data.text = tex;

                            this.listDir.push(data.data);

                            this.trigger('update', this.listDir);
                        }.bind(this));



                    this.map.addSource(data.data.id, {
                        "type": "geojson",
                        "data": {
                            "type": "FeatureCollection",
                            "features": [{
                                "type": "Feature",
                                "geometry": {
                                    "type": "Point",
                                    "coordinates": [data.data.loc[0], data.data.loc[1]]
                                },
                                "properties": {
                                    "marker-symbol": "marker"
                                }
                            }]
                        }
                    });

                    this.map.addLayer({
                        "id": data.data.id,
                        "type": "symbol",
                        "source": data.data.id,
                        "layout": {
                            "icon-image": "{marker-symbol}-15",
                            "text-field": "{title}",
                            "text-font": ["Open Sans Semibold", "Arial Unicode MS Bold"],
                            "text-offset": [0, 0.6],
                            "text-anchor": "top"
                        }
                    });
                }
            }.bind(this));
    },
    ready: function () {






        mapboxgl.accessToken = 'pk.eyJ1IjoiY3Jpc3R5YW5zdiIsImEiOiJjaW80ajZnajUwMWl5djZrcTh0MTM5Y3JzIn0.hyuT6lS1B3q2w-qdXvXXdg';
        this.map = new mapboxgl.Map({
            container: 'map', // container id
            style: 'mapbox://styles/cristyansv/cio4k1wzt005nainm44yw94ug', //stylesheet location
            center: [-75.5723, 6.20715], // starting position
            zoom: 13,
            hash: true
        });


            this.map.on('click', function (e) {
            this.addPoint(e);
        }.bind(this));
    }
});

module.exports = MapStore;