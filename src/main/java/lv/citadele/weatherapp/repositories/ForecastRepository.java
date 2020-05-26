package lv.citadele.weatherapp.repositories;

import lv.citadele.weatherapp.entities.Forecast;
import org.springframework.data.repository.CrudRepository;

public interface ForecastRepository extends CrudRepository<Forecast, Integer> {

}
