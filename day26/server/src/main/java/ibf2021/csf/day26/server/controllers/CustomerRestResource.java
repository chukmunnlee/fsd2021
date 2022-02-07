package ibf2021.csf.day26.server.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/api/customer", produces=MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin("*")
public class CustomerRestResource {

	@GetMapping
	public ResponseEntity<String> getCustomer(
			@RequestParam(required=false) String id, @RequestParam(required=false) String comments) {

		JsonObject fred = Json.createObjectBuilder()
			.add("name", "fred")
			.add("address", "1 bedrock")
			.add("email", "fred@gmail.com")
			.build();

		System.out.printf("id = %s, comments = %s\n", id, comments);

		/*
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");

		System.out.println(">>> return");

		return ResponseEntity.ok().headers(headers).body(fred.toString());
		*/

		return ResponseEntity.ok(fred.toString());
	}

}
