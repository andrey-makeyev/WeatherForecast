package lv.aqal.weatherforecast;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;


@ConfigurationProperties("app.weather")
public class WeatherAppProperties {
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Valid
	private final Api api = new Api();

	/**
	 * Comma-separated list of locations to display. Each entry should have the
	 * form "Country/City".
	 */
	private List<String> locations = Arrays.asList("UK/London", "Russia/Moscow");

	public Api getApi() {
		return this.api;
	}

	public List<String> getLocations() {
		return this.locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public static class Api {

		/**
		 * API key of the OpenWeatherMap service.
		 */
		@NotNull
		private String key;

		public String getKey() {
			return this.key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}

}
