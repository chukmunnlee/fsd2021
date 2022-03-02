package ibf2021.paf.authjwt.controllers;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.paf.authjwt.services.AuthenticateService;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@RestController
@RequestMapping(path="/api/authenticate")
public class AuthenticateRestController {

	@Autowired
	private AuthenticateService authSvc;

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postAuthenticate(@RequestBody String body) {

		JsonObject obj;

		try {
			JsonReader reader = Json.createReader(new ByteArrayInputStream(body.getBytes("UTF-8")));
			obj = reader.readObject();

			Optional<JsonObject> opt = authSvc.authenticate(obj.getString("username"), obj.getString("password"));
			if (opt.isPresent())
				return ResponseEntity.ok(opt.get().toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		obj = Json.createObjectBuilder()
			.add("error", "Cannot authenticate")
			.build();

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(obj.toString());
	}
}
