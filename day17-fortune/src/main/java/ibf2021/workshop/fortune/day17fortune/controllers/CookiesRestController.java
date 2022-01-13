package ibf2021.workshop.fortune.day17fortune.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.workshop.fortune.day17fortune.services.*;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;

@RestController
@RequestMapping(path = "/cookies", produces = MediaType.APPLICATION_JSON_VALUE)
public class CookiesRestController {

	@Autowired
	private FortuneCookie fortuneCookie;

	@GetMapping
	public ResponseEntity<String>  getCookies(
			@RequestParam(defaultValue = "1") Integer count) {

		if ((count < 1) || (count > 10)) 
			return 
				ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(
						Json.createObjectBuilder()
							.add("error", "count must be between 1 and 10 inclusive")
							.build()
							.toString()
					);

		List<String> cookies = this.fortuneCookie.getCookies(count);
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

		cookies
			.parallelStream()
			//.stream()
			//.forEach(v -> arrBuilder.add(v));
			.reduce(
					arrBuilder,  //identity
					(ab, item) -> ab.add(item), // accumulator
					(ab0, ab1) -> {
						JsonArray a = ab1.build();
						for (int i = 0; i < a.size(); i++)
							ab0.add(a.get(i));
						return ab0;
					} // combiner
				);

		JsonObjectBuilder jsonObj = Json.createObjectBuilder()
				.add("cookies", arrBuilder)
				.add("timestamp", System.currentTimeMillis());

		return ResponseEntity.ok(jsonObj.build().toString());
	}
    
}
