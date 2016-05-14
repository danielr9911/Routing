if(!global.hasOwnProperty('db')){
    var mongoose = require('mongoose'); // Using Mongoose Schema.
    var dbName = 'geo'; // Selecting Kanvas DB
    // Public / External IP from DO.
    // 104.131.25.164
    mongoose.connect('mongodb://geo:22geo@' + 'dmaz.co' + ':27017/' + dbName);

    /**
     * Mongoose global schemes
     * @param {scheme} requires the model schema, based in mongoose
     * @returns {scheme} Global schema db
     */

    global.db = {
        mongoose: mongoose,
        // custom schema
        Nodes: require('./nodes')(mongoose),
    };

}

module.exports = global.db;