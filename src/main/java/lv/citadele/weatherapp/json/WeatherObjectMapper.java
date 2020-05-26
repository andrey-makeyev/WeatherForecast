package lv.citadele.weatherapp.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lv.citadele.weatherapp.models.Weather;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class WeatherObjectMapper extends ObjectMapper {

    public WeatherObjectMapper() {
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Weather.class, new WeatherDeserializer());
        this.registerModule(module);
    }


}