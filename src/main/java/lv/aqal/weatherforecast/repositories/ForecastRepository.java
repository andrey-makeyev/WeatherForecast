package lv.aqal.weatherforecast.repositories;

import lv.aqal.weatherforecast.entities.Forecast;
import org.springframework.data.repository.CrudRepository;

public interface ForecastRepository extends CrudRepository<Forecast, Integer> {

}
