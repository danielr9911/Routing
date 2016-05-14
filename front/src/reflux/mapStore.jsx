var Reflux = require('reflux');

var MapActions = require('./mapActions.jsx');
var http = require('../services/http');

var MapStore = Reflux.createStore({
    listenables: [MapActions],
    init: function () {

        this.listDir = [];

    },
    addPoint: function (e) {


            var match = http.post('/match', {
                lon: e.latLng.lng(),
                lat: e.latLng.lat()
            });

            match.then(function (data) {
                if(data.status){

                    var latlng = {lat: data.data.loc[1], lng: data.data.loc[0]};

                    var marker = new google.maps.Marker({
                        position: latlng,
                        map: this.map,
                        title: 'Hello World!'
                    });

                    this.geocoder.geocode({'location': latlng}, function(results, status) {
                        if (status === google.maps.GeocoderStatus.OK) {

                            data.data.text = results[0].formatted_address;
                            this.listDir.push(data.data);

                            console.log(this.listDir);

                            this.trigger('update', this.listDir);

                        }
                    }.bind(this));

                }
            }.bind(this));
    },
    ready: function (){
        function loadMap() {
            if(typeof google !== 'undefined'){
                this.map = new google.maps.Map(document.getElementById('map'), {
                    center: {lat: 6.20199, lng: -75.59398},
                    zoom: 14
                });

                this.geocoder = new google.maps.Geocoder;


                this.map.addListener('click', this.addPoint);


            }else{
                setTimeout(function () {
                    loadMap.bind(this)();
                }.bind(this), 20);
            }
        }

        loadMap.bind(this)();
    }
});

module.exports = MapStore;