package lv.citadele.weatherapp.json;


import lv.citadele.weatherapp.models.Weather;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonUtil {

    @Autowired
    WeatherObjectMapper mapper;

    private static final Logger LOG = Logger.getLogger(JsonUtil.class);

    public Weather parseJson(String json) {
        System.setProperty("file.encoding", "UTF-8");
        Weather weather = null;

        LOG.info("JSON: " + json);
        try {
            weather = mapper.readValue(json, Weather.class);

        } catch (IOException e) {
            LOG.error("IO exception", e);
        }
        return weather;
    }
}