package ifb2021.paf.day33.models;

import jakarta.json.JsonObject;

public class LineItem {
	private Integer  itemId;
	private String description;
	private Integer quantity;
	private Float unitPrice;

	public Integer getItemId() { return itemId; }
	public void setItemId(Integer itemId) { this.itemId = itemId; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public Integer getQuantity() { return quantity; }
	public void setQuantity(Integer quantity) { this.quantity = quantity; }

	public Float getUnitPrice() { return unitPrice; }
	public void setUnitPrice(Float unitPrice) { this.unitPrice = unitPrice; }

	public static LineItem create(JsonObject o) {

		final LineItem li = new LineItem();
		try {
			li.setItemId(o.getInt("itemId"));
		} catch (Exception ex) { }
		li.setDescription(o.getString("description"));
		li.setQuantity(o.getJsonNumber("quantity").intValue());
		li.setUnitPrice((float)o.getJsonNumber("unitPrice").doubleValue());

		return li;
	}
}
