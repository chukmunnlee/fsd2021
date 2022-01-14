package ibf2021.ssf.weather.day18.controllers;

import java.util.List;
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
import ibf2021.ssf.weather.day18.services.WeatherService;

@Controller
@RequestMapping(path = "/weather")
public class WeatherController {

    private final Logger logger = Logger.getLogger(Day18Application.class.getName());

    @Autowired
    private WeatherService weatherSvc;

    @GetMapping
    public String getWeather(@RequestParam(required = true) String city, Model model) {

        logger.log(Level.INFO, "City: %s".formatted(city));

        try {
            List<Weather> weather = weatherSvc.getWeather(city);
            if (weather.size() > 0)
                city = weather.get(0).getCityName();
            model.addAttribute("weather", weather);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Warning: %s".formatted(ex.getMessage()));
        }
        
        model.addAttribute("city", city);

        return "weather";
    }
    
}
