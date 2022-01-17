package ibf2021.ssf.marvel.day19.controllers;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2021.ssf.marvel.day19.models.Marvel;
import ibf2021.ssf.marvel.day19.services.MarvelService;

@Controller
@RequestMapping(path = {"/", "/index.html"})
public class MainController {

    @Value("${marvel.characters}")
    private String marvelChar;

    @Autowired
    private MarvelService marvelSvc;

    private List<String> characters;
    private Random rand = new SecureRandom();

    public MainController() {
        System.out.println(">>>>> In constructor: " + marvelChar);
    }

    @PostConstruct
    public void init() {
        System.out.println(">>>>> In init: " + marvelChar);
        characters = new LinkedList<>();
        for (String n: marvelChar.split(","))
            characters.add(n.trim());
        System.out.println("list = " + characters);
    }

    @GetMapping
    public String get(Model model) {
        String mc = getRandomCharacter();
        List<Marvel> result = marvelSvc.getCharacter(mc);
        model.addAttribute("characters", result);
        return "main";
    }

    private String getRandomCharacter() {
        return characters.get(rand.nextInt(characters.size()));
    }
    
}
