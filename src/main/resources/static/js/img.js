$(document).ready(function () {
    "use strict";
    var imgWeather = $("img");
    imgWeather.attr('src', "/img/banner.jpg");

    if (weather.toLowerCase() === "rain") {
        imgWeather.attr('src', "/img/rain.jpg");
    } else if (weather.toLowerCase() === "clear") {
        imgWeather.attr('src', "/img/sunny.jpg");
    } else if (weather.toLowerCase() === "snow") {
        imgWeather.attr('src', "/img/snow.jpg");
    } else if (weather.toLowerCase() === "broken clouds") {
        imgWeather.attr('src', "/img/broken_clouds.jpg");
    } else if (weather.toLowerCase() === "clouds") {
        imgWeather.attr('src', "/img/clouds.jpg");
    } else { imgWeather.attr('src', "/img/default.png") }

});