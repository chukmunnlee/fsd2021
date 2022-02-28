package ibf2021.paf.day34.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post/s3")
public class PostS3RestController {

    @PostMapping
    public ResponseEntity<String> post() {

        // Print out the contents of the post from Angular

        return ResponseEntity.ok("{}");
    }
    
}
