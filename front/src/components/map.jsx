var React = require('react');
var Dir = require('./Dir.jsx');

var MapStore = require('../reflux/mapStore.jsx');
var MapActions = require('../reflux/mapActions.jsx');

var style = {
    position: 'absolute',
    top: 0,
    bottom: 0,
    width: '100%'
};

var Map = React.createClass({
    getInitialState: function () {
        return {
            dirs: []
        }
    },
    componentWillUnmount: function() {
        this.unsubscribe();
    },
    componentDidMount: function () {
        this.unsubscribe = MapStore.listen(this.onChange);
        MapActions.ready();
    },
    onChange: function (event, dirs) {
        this.setState({dirs: dirs});
    },
    onClick: function () {
        MapActions.search();
    },
    render: function () {
        return (
            <div>
                <div style={style} id="map"></div>
                <Dir handler={this.onClick} dirs={this.state.dirs}/>
            </div>
        )
    }
});

module.exports = Map;