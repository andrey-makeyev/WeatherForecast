package lv.citadele.weatherapp.services;

import lv.citadele.weatherapp.entities.Forecast;
import lv.citadele.weatherapp.repositories.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForecastServiceImpl implements ForecastService {

    private ForecastRepository forecastRepository;
    private Object Null;

    @Autowired
    public void setForecastRepository(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public Iterable<Forecast> listAllForecasts() {
        return forecastRepository.findAll();
    }

    @Override
    public Forecast getForecastById(Integer id) {
        return forecastRepository.findById(id).orElse((Forecast) Null);
        
    }

    @Override
    public Forecast saveForecast(Forecast forecast) {
        return forecastRepository.save(forecast);
    }

    @Override
    public void deleteForecast(Integer id) {
        forecastRepository.deleteById(id);
    }

}
