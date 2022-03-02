package ibf2021.paf.authjwt.controllers.secure;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/secure/api/fortunecookie", produces=MediaType.APPLICATION_JSON_VALUE)
public class FortuneCookieRestController {

	@GetMapping
	public ResponseEntity<String> getFortuneCookie() {
		final JsonObject obj = Json.createObjectBuilder()
			.add("cookie", "hello, world")
			.build();
		return ResponseEntity.ok(obj.toString());
	}
}
