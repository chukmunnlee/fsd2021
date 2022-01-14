package ibf2021.ssf.weather.day18.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2021.ssf.weather.day18.Day18Application;
import ibf2021.ssf.weather.day18.models.Weather;
import ibf2021.ssf.weather.day18.services.WeatherCacheService;
import ibf2021.ssf.weather.day18.services.WeatherService;

@Controller
@RequestMapping(path = "/weather")
public class WeatherController {

    private final Logger logger = Logger.getLogger(Day18Application.class.getName());

    @Autowired
    private WeatherService weatherSvc;

    @Autowired
    private WeatherCacheService weatherCacheSvc;

    @GetMapping
    public String getWeather(@RequestParam(required = true) String city, Model model) {

        logger.log(Level.INFO, "City: %s".formatted(city));

        Optional<List<Weather>> opt = weatherCacheSvc.get(city);

        List<Weather> weatherList = Collections.EMPTY_LIST;

        if (opt.isPresent()) 
            weatherList = opt.get();
        else
            try {
                weatherList = weatherSvc.getWeather(city);
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Warning: %s".formatted(ex.getMessage()));
            }

        if (weatherList.size() > 0)
            city = weatherList.get(0).getCityName();
        
        model.addAttribute("weather", weatherList);
        model.addAttribute("city", city);

        return "weather";
    }
    
}
