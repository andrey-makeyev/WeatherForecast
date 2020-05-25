package lv.aqal.weatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)

public class WeatherApp {


	public static void main(String[] args) {
		SpringApplication.run(WeatherApp.class, args);
	}

}
