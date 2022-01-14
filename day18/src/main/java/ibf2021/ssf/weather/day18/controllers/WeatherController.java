package ibf2021.ssf.weather.day18.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2021.ssf.weather.day18.services.WeatherService;

@Controller
@RequestMapping(path = "/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherSvc;

    @GetMapping
    public String getWeather(@RequestParam(required = true) String city, Model model) {

        System.out.println("weather: " + city);

        try {
            weatherSvc.getWeather(city);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        model.addAttribute("city", city);

        return "weather";
    }
    
}
