package lv.citadele.weatherapp.controllers;

import lv.citadele.weatherapp.models.Weather;
import lv.citadele.weatherapp.services.WeatherService;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Objects;


@Controller
public class WeatherController {

    private static final Logger LOG = Logger.getLogger(WeatherController.class);

    @Autowired
    WeatherService weatherService;

    @RequestMapping("/")
    public String index(Model model) {

        return "index";
    }

    @RequestMapping(value = "/showWeather", method = RequestMethod.POST)
    public ModelAndView getCity(@RequestParam String city, @RequestParam String user, @RequestParam String longitude, @RequestParam String latitude) {
        String text;
        ModelAndView modelAndView = new ModelAndView();
        if ((city.trim().equals("")) && (longitude == null || longitude.trim().equals("")) && (latitude == null || latitude.trim().equals(""))) {
            text = LocalDateTime.now() + " Enter city or geolocation data.";
            getException(modelAndView, null, text);
        } else if (!city.trim().equals("") && !longitude.trim().equals("") && !latitude.trim().equals("")) {
            text = LocalDateTime.now() + " Either the city or geolocation data must be entered, but not all together.";
            getException(modelAndView, null, text);
        } else if (user == null || user.trim().equals("")) {
            text = LocalDateTime.now() + " Username is empty.";
            getException(modelAndView, null, text);
        } else if (city.trim().equals("") && (!(NumberUtils.isNumber(latitude) && NumberUtils.isNumber(longitude)))) {
            text = LocalDateTime.now() + " \n" +
                    "Geolocation fields should only contain numbers.";
            getException(modelAndView, null, text);
        } else {
            LOG.info("User " + user + " come " + LocalDateTime.now());

            Weather weather = null;
            if (!city.trim().equals("")) {
                weather = weatherService.getWeatherByCity(city);
            } else if (!(longitude.trim().equals("") && latitude.trim().equals(""))) {
                weather = weatherService.getWeatherByGeo(longitude, latitude);
            }

            if (weather != null) {
                if (Objects.nonNull(weather.getCity().getCountry())) {
                    modelAndView.addObject("country", weather.getCity().getCountry());
                } else {
                    modelAndView.addObject("country", "Country not defined");
                    if (city.equals("")) {
                        city = Objects.nonNull(weather.getCity().getName()) ? weather.getCity().getName() : "City not defined. Coordinates: lon = " + longitude + ", lat = " + latitude;
                    }

                }

                modelAndView.addObject("city", city);
                modelAndView.setViewName("weather");

                modelAndView.addObject("temp", weather.getData().getDataMain().getTemp());
                modelAndView.addObject("pressure", weather.getData().getDataMain().getPressure());
                modelAndView.addObject("humidity", weather.getData().getDataMain().getHumidity());
                modelAndView.addObject("rain", weather.getData().getPrecipitation().getDescription());
                modelAndView.addObject("precipitation", weather.getData().getPrecipitation().getMain());

                LOG.info(LocalDateTime.now() + " User " + user + " requested the weather for city " + city + " and received response: " + weather.toString());
            } else {
                if (!city.trim().equals("")) {
                    text = LocalDateTime.now() + " User " + user + " requested the weather for city " + city + ". City not found.";
                } else {
                    text = LocalDateTime.now() + " User " + user + " requested the weather by geolocation: longitude " + longitude + " and latitude " + latitude + ". Geolocation not found.";
                }
                getException(modelAndView, null, text);
                LOG.error(text);
            }
        }

        return modelAndView;
    }

    private void getException(ModelAndView modelAndView, Exception e, String text) {
        modelAndView.setViewName("exception");
        modelAndView.addObject("message", text);
        if (e != null) {
            LOG.error(text, e);
        } else {
            LOG.error(text);
        }

    }
}

