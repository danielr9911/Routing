var React = require('react');



var style ={
    position: 'fixed',
    top: 0,
    left: 0,
    width: 300,
    height: '100%',
    backgroundColor: '#2b2b2b',
    color: 'white',
    fontFamily: "'Roboto', sans-serif",
    paddingLeft: 10,
    paddingRight: 10
};

var title = {
    textAlign: 'center'
};

var lista = {
    width: 280,
    backgroundColor: '#1a1a1a',
    // padding: 10,
    borderRadius: 5,
    padding: 10,
    marginBottom: 5
};

var Dir = React.createClass({
    onClick: function () {
        this.props.handler();
    },
    render: function () {

        var dirs = this.props.dirs.map(function (dir) {
           return (
               <div key={dir.id} style={lista}>
                   {dir.text}
               </div>
           )
        });

        return (
            <div style={style}>
                <h1 style={title}>Domicilios</h1>
                <div >
                    {dirs}
                </div>
                <button onClick={this.onClick}>Ruta</button>
            </div>
        )
    }
});

module.exports = Dir;