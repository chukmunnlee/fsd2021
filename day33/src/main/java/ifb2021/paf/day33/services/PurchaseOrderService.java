package ifb2021.paf.day33.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ifb2021.paf.day33.OrderTooLargeException;
import ifb2021.paf.day33.models.LineItem;
import ifb2021.paf.day33.models.PurchaseOrder;
import ifb2021.paf.day33.models.PurchaseOrderTotal;
import ifb2021.paf.day33.repositories.LineItemRepository;
import ifb2021.paf.day33.repositories.PurchaseOrderRepository;

import static ifb2021.paf.day33.repositories.SQL.*;

@Service
public class PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository poRepo;
	
	@Autowired
	private JdbcTemplate template;

	@Autowired
	private LineItemRepository liRepo;

	@Transactional(rollbackFor = OrderTooLargeException.class)
	public Integer createPurchaseOrder(final PurchaseOrder po) throws OrderTooLargeException {
		final Integer orderId = poRepo.insertPurchaseOrder(po);
		// Calculate the order size
		float total = 0;
		for (LineItem li: po.getLineItems())
			total += li.getQuantity() * li.getUnitPrice();
		if (total > 10000) {
			OrderTooLargeException ex = new OrderTooLargeException("Order exceed 10000: %f".formatted(total));
			ex.setPo(po);
			throw ex;
		}

		liRepo.addLineItems(orderId, po.getLineItems());
		return orderId;
	}

	public List<PurchaseOrderTotal> getPurchaseOrderTotals() {
		final SqlRowSet rs = template.queryForRowSet(SQL_PURCHASE_ORDER_TOTAL);
		List<PurchaseOrderTotal> result = new LinkedList<>();
		while (rs.next())
			result.add(PurchaseOrderTotal.create(rs));
		return result;
	}
}
