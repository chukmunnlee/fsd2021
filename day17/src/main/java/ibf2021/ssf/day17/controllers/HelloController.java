package ibf2021.ssf.day17.controllers;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/hello", produces = MediaType.TEXT_HTML_VALUE)
public class HelloController {

    @GetMapping
    public String getHello(Model model) {

        model.addAttribute("message", 
            "Hello, the time/date is %s".formatted((new Date()).toString()));

        return "hello";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postHello(@RequestBody MultiValueMap<String, String> form) {
        String name = form.getFirst("name");
        String email = form.getFirst("email");

        System.out.printf("name: %s, email: %s\n", name, email);

        return "completed";
    }
    
}
