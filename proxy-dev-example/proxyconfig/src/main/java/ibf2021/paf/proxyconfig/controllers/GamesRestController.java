package ibf2021.paf.proxyconfig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.paf.proxyconfig.repositories.GameRepository;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@RestController
@RequestMapping(path="/api", produces=MediaType.APPLICATION_JSON_VALUE)
public class GamesRestController {

	@Autowired
	private GameRepository gameRepo;

	@GetMapping(path="/games")
	public ResponseEntity<String> getGames(@RequestParam(defaultValue="10") Integer limit
			, @RequestParam(defaultValue="0") Integer offset) {

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		gameRepo.listGames(limit, offset).stream()
			.forEach(g -> arrBuilder.add(g.toJson()));

		return ResponseEntity.ok(arrBuilder.build().toString());
	}
}
