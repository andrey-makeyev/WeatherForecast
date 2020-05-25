package lv.aqal.weatherforecast.services;

import lv.aqal.weatherforecast.entities.Forecast;

public interface ForecastService {

    Iterable<Forecast> listAllForecasts();

    Forecast getForecastById(Integer id);

    Forecast saveForecast(Forecast forecast);

    void deleteForecast(Integer id);

}
