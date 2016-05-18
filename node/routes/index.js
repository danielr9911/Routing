var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function (req, res, next) {
    res.render('index', {title: 'Express'});
});

router.post('/match', function (req, res) {


    console.log(req.body);

    db.Nodes.findOne({
        loc: {$near: [req.body.lon, req.body.lat], $maxDistance: 0.005}
    }, function (error, data) {
        if (error) {
            console.log(error)
        } else {

            if (data == null) {
                res.send({status: false, data: data});
            } else {
                res.send({status: true, data: data});
            }


        }
    });
});

var fs = require('fs');

router.post('/route', function (req, res) {


    fs.exists('./Routing/query.txt', function (exists) {
        if (exists) {
            fs.unlink('./Routing/query.txt');
        } else {
            console.log('File not found, so not deleting.');
        }

        var nodeFile = fs.createWriteStream('./Routing/query.txt');
        var po = req.body.points;

        for (var i = 0; i < po.length; i++) {
            nodeFile.write(po[i]+'\n');
        }
        nodeFile.end();

        var dir = {
            cwd: './Routing'
        };


        var spawn = require('child_process').spawn;
        var prc = spawn('java', ['Dijkstra'], dir);


        var solution = "";

        prc.stdout.setEncoding('utf8');
        prc.stdout.on('data', function (data) {

            console.log('data');
            solution += data;
        });

        prc.on('close', function (code) {
            console.log(solution);

            var cor = solution.split(",");
            cor.splice(cor.length - 1, 1);
            cor.push(cor[0]);

            //aaaaaaaa
            var find = [];

            cor.forEach(function (ele) {
                find.push(db.Nodes.findOne({id: ele}).exec());
            });

            Promise.all(find).then(function (values) {
                res.json(values);
            })

        });


    });


//


});


module.exports = router;
