module.exports = function (mongoose) {
    var Schema = mongoose.Schema;
    var ConversionSchema = new Schema({
        id: {type: String},
        adj: {type: [String]},
        loc: {
            type: [Number],  // [<longitude>, <latitude>]
            index: '2d'      // create the geospatial index
        }
    });
    return mongoose.model('Nodes', ConversionSchema);
};