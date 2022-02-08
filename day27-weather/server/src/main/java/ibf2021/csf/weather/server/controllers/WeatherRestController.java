package ibf2021.csf.weather.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.csf.weather.server.models.Weather;
import ibf2021.csf.weather.server.services.WeatherService;

@RestController
@RequestMapping(path="/api/weather", produces=MediaType.APPLICATION_JSON_VALUE)
public class WeatherRestController {

	@Autowired
	private WeatherService weatherSvc;

	@GetMapping(path="{city}")
	public ResponseEntity<String> getWeather(@PathVariable String city) {
		Weather weather = weatherSvc.getWeather(city);
		return ResponseEntity.ok(weather.toJson().toString());
	}
}
