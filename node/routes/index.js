var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.post('/match', function (req, res) {
  db.Nodes.findOne({
    loc: { $near : [ req.body.lon, req.body.lat ], $maxDistance: 0.005 }
  }, function (error, data) {
    if(error){
      console.log(error)
    }else{
      res.send(data);
    }
  });
});


module.exports = router;
