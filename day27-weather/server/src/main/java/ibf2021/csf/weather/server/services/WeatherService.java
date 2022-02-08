package ibf2021.csf.weather.server.services;

import java.util.Objects;
import java.util.logging.Logger;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2021.csf.weather.server.ServerApplication;
import ibf2021.csf.weather.server.models.Weather;

@Service
public class WeatherService {

	public static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
	public static final String ENV_OPENWEATHERMAP_KEY = "OPENWEATHERMAP_KEY";

	private final Logger logger = Logger.getLogger(ServerApplication.class.getName());

	private final String key;

	public WeatherService() {
		key = System.getenv(ENV_OPENWEATHERMAP_KEY);

		if (Objects.isNull(key))
			logger.warning("%s key is not set".formatted(ENV_OPENWEATHERMAP_KEY));
	}

	public Weather getWeather(String city) {
		final String url = UriComponentsBuilder
			.fromUriString(WEATHER_URL)
			.queryParam("q", city.replaceAll(" ", "\\+"))
			.queryParam("units", "metric")
			.queryParam("appid", key)
			.toUriString();

		final RequestEntity<Void> req = RequestEntity.get(url).build();

		final RestTemplate template = new RestTemplate();

		final ResponseEntity<String> resp = template.exchange(req, String.class);

		return Weather.toWeather(resp.getBody());
	}


}
