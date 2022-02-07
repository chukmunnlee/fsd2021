package ibf2021.csf.day26.server.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/api/customer", produces=MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestResource {

	@GetMapping
	public ResponseEntity<String> getCustomer() {
		JsonObject fred = Json.createObjectBuilder()
			.add("name", "fred")
			.add("address", "1 bedrock")
			.add("email", "fred@gmail.com")
			.build();

		return ResponseEntity.ok(fred.toString());
	}

}
