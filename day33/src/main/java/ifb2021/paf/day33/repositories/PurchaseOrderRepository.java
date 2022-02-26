package ifb2021.paf.day33.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ifb2021.paf.day33.models.PurchaseOrder;

import static ifb2021.paf.day33.repositories.SQL.*;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class PurchaseOrderRepository {

	@Autowired
	private JdbcTemplate template;

	public Integer insertPurchaseOrder(final PurchaseOrder po) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(conn -> {
			PreparedStatement ps = conn.prepareStatement(
					SQL_INSERT_PURCHASE_ORDER, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, po.getName());
			ps.setString(2, po.getEmail());
			return ps;
		}, keyHolder);

		// auto_increment returns a BigInteger
		BigInteger bigInt = (BigInteger)keyHolder.getKey();
		return bigInt.intValue();
	}
}
