var Fetch = require('whatwg-fetch');

var baseUrl = 'http://localhost:3000';

var service = {
    get: function (url) {
        return fetch(url)
            .then(function (response) {
                return response.json();
            });
    },
    post: function (url, body) {
        return fetch(baseUrl+url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        }).then(function (response) {
            return response.json();
        });
    }
};

module.exports = service;