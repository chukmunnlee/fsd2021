package ifb2021.paf.day33.models;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


public class PurchaseOrderTotal {
	private Integer  orderId;
	private String name;
	private Float total;

	public Integer getOrderId() { return orderId; }
	public void setOrderId(Integer orderId) { this.orderId = orderId; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public Float getTotal() { return total; }
	public void setTotal(Float total) { this.total = total; }

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("orderId", getOrderId())
			.add("name", getName())
			.add("total", getTotal())
			.build();
	}

	public static PurchaseOrderTotal create(SqlRowSet rs) {
		final PurchaseOrderTotal po = new PurchaseOrderTotal();
		po.setOrderId(rs.getInt("ord_id"));
		po.setName(rs.getString("name"));
		po.setTotal(rs.getFloat("total"));
		return po;
	}

}
