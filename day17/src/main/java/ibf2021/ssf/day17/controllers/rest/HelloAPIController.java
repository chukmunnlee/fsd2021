package ibf2021.ssf.day17.controllers.rest;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloAPIController {

    @GetMapping
    public ResponseEntity<String> getHello() {
        // { "message": "The current time/date is ..." }
        String jsonResp = "{ \"message\": \"The current time/date is %s\" }"
                .formatted((new Date()).toString());
        return ResponseEntity.ok(jsonResp);
    }
}
