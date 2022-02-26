package ifb2021.paf.day33.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifb2021.paf.day33.models.PurchaseOrder;
import ifb2021.paf.day33.models.PurchaseOrderTotal;
import ifb2021.paf.day33.services.PurchaseOrderService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/api/order", produces=MediaType.APPLICATION_JSON_VALUE)
public class PurchaseOrderRestController {

	@Autowired
	private PurchaseOrderService poSvc;

	@GetMapping(path="/values")
	private ResponseEntity<String> getOrderValue() {
		List<PurchaseOrderTotal> poTotals = poSvc.getPurchaseOrderTotals();
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		poTotals.stream()
			.map(v -> v.toJson())
			.forEach(arrayBuilder::add);
		return ResponseEntity.ok(arrayBuilder.build().toString());
	}

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postOrder(@RequestBody String json) {
		PurchaseOrder order = null;
		JsonObject resp;
		try {
			order = PurchaseOrder.create(json);
		} catch (Exception ex) {
			ex.printStackTrace();
			resp = Json.createObjectBuilder()
				.add("error", ex.getMessage())
				.build();
			return ResponseEntity.badRequest().body(resp.toString());
		}

		Integer orderId = poSvc.createPurchaseOrder(order);
		resp = Json.createObjectBuilder()
			.add("orderId", orderId)
			.build();
		return ResponseEntity.ok(resp.toString());
	}
}
