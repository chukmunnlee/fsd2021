package ibf2021.csf.day26.server.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path="/api/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRestController {

    @PostMapping(consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> postMethodName(@RequestBody MultiValueMap<String, String> form) {

        String name = form.getFirst("name");
        String email = form.getFirst("email");

        System.out.printf("name: %s, email: %s\n", name, email);

        JsonObjectBuilder payload = Json.createObjectBuilder();

        if (name.trim().toLowerCase().startsWith("justin")) {
            payload.add("message", 
                "Unfortunately for you, your name begins with Justin");
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload.build().toString());
        }
        
        payload.add("message", 
            "%s, you have been registered".formatted(name));
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(payload.build().toString());
    }
    
    
}
