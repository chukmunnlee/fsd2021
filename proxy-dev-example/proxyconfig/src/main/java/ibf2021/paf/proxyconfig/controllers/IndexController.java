package ibf2021.paf.proxyconfig.controllers;

import com.chuklee.code.statetransfer.TransferState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2021.paf.proxyconfig.repositories.GameRepository;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@Controller
@RequestMapping(path={"/", "/index.html"})
public class IndexController {

	@Autowired
	private GameRepository gameRepo;

	@GetMapping
	public String getIndex(Model model) {
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		gameRepo.listGames(20, 10).stream()
			.forEach(g -> arrBuilder.add(g.toJson()));

		TransferState state = new TransferState("myapp");
		state.add("games", arrBuilder.build());

		model.addAttribute("serializedState", state.render());

		return "index";
	}
}
