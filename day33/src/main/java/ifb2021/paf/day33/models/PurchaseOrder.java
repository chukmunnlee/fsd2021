package ifb2021.paf.day33.models;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


public class PurchaseOrder {
	private Integer  orderId;
	private String name;
	private String email;
	private List<LineItem> lineItems = new LinkedList<>();

	public Integer getOrderId() { return orderId; }
	public void setOrderId(Integer orderId) { this.orderId = orderId; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public void addLineItem(LineItem lineItem) { this.lineItems.add(lineItem); }
	public List<LineItem> getLineItems() { return this.lineItems; }
	public void setLineItem(List<LineItem> lineItems) { this.lineItems = lineItems; }

	public static PurchaseOrder create(String jsonStr) throws Exception {
		JsonReader reader = Json.createReader(new ByteArrayInputStream(jsonStr.getBytes()));
		return create(reader.readObject());
	}
	public static PurchaseOrder create(JsonObject o) {
		final PurchaseOrder po = new PurchaseOrder();
		try {
			po.setOrderId(o.getInt("orderId"));
		} catch (Exception ex) { }
		po.setName(o.getString("name"));
		po.setEmail(o.getString("email"));

		List<LineItem> lineItems = o.getJsonArray("lineItems")
				.stream()
				.map(li -> LineItem.create((JsonObject)li))
				.toList();
		po.setLineItem(lineItems);

		return po;
	}

}
