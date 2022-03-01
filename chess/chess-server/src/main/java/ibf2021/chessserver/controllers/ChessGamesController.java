package ibf2021.chessserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.chessserver.services.ChessRepositoryService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@RestController
@RequestMapping("/games")
public class ChessGamesController {

	@Autowired
	private ChessRepositoryService chessRepoSvc;

	@GetMapping(path="open", produces=MediaType.APPLICATION_JSON_VALUE)
	public String getOpenGames() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		chessRepoSvc.getOpenGames().stream()
			.forEach(g -> builder.add(g));

		return builder.build().toString();
	}
}
