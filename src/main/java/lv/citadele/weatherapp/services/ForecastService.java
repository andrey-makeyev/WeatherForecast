package lv.citadele.weatherapp.services;

import lv.citadele.weatherapp.entities.Forecast;

public interface ForecastService {

    Iterable<Forecast> listAllForecasts();

    Forecast getForecastById(Integer id);

    Forecast saveForecast(Forecast forecast);

    void deleteForecast(Integer id);

}
