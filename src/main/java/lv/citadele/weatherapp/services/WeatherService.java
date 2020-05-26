package lv.citadele.weatherapp.services;


import lv.citadele.weatherapp.models.Weather;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lv.citadele.weatherapp.json.JsonUtil;

import java.net.URI;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Service
public class WeatherService {

    private static final Logger LOG = Logger.getLogger(WeatherService.class);
    private final String COMMON_URL = "http://api.openweathermap.org/data/2.5/forecast?";
    private final String APPID = "&units=metric&APPID=a0b2588c1445f2c91d64396d439f4f16";

    @Autowired
    JsonUtil jsonUtil;

    Future<Weather> future;

    ExecutorService service = Executors.newFixedThreadPool(1);

    public Weather getWeatherByCity(String city) {
        return getWeather(COMMON_URL + "q=" + city + APPID);
    }

    public Weather getWeatherByGeo(String longitude, String latitude) {
        return getWeather(COMMON_URL + "lat=" + latitude + "&lon=" + longitude + APPID);
    }

    private Weather getWeather(String url) {
        Callable<Weather> callable = () -> {
            Weather weather = null;
            String json = null;

            RestTemplate restTemplate = new RestTemplate();
            try {
                json = restTemplate.getForObject(new URI(url), String.class);

            } catch (Exception e) {
                LOG.error("Invalid format URI");
            }


            try {
                weather = jsonUtil.parseJson(json);
            } catch (Exception e) {
                LOG.error("Invalid format json", e);
            }
            return weather;
        };

        Future<Weather> future = service.submit(callable);
        try {
            return future.get();
        } catch (InterruptedException e) {
            LOG.error("Callable Exception future = " + future, e);
        } catch (Exception e) {
            LOG.error(e);
        }
        return null;
    }


}
