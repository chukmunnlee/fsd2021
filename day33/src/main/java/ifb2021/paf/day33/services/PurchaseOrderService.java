package ifb2021.paf.day33.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

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

	public Integer createPurchaseOrder(final PurchaseOrder po) {
		final Integer orderId = poRepo.insertPurchaseOrder(po);
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
