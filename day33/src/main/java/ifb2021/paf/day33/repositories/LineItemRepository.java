package ifb2021.paf.day33.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ifb2021.paf.day33.models.LineItem;

import static ifb2021.paf.day33.repositories.SQL.*;
import static java.sql.Types.*;

@Repository
public class LineItemRepository {

	@Autowired
	private JdbcTemplate template;

	public int[] addLineItems(Integer orderId, Collection<LineItem> lineItems) {
		List<Object[]> params = lineItems.stream()
				.map(v -> {
					Object[] row = new Object[4];
					row[0] = orderId;
					row[1] = v.getDescription();
					row[2] = v.getQuantity();
					row[3] = v.getUnitPrice();
					return row;
				})
				.toList();
		return template.batchUpdate(SQL_INSERT_LINE_ITEM, params);
	}
}
