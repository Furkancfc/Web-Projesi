package dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Account;
import model.CartItem;
import model.Orders;
import model.interfaces.IGeneric;

public abstract class OrderDao extends Dao<model.Orders> {

	public class OrderMapper implements RowMapper<model.Orders> {

		@Override
		public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
			Orders order = (Orders) IGeneric.getInstance(rs.getBytes("obj"));
			if (order == null) {
				String orderId = rs.getString("orderId");
				String accountId = rs.getString("accountId");
				String cartItemId = rs.getString("cartItemId");
				Account ac = (Account) IGeneric.getInstance(rs.getBytes("accountObj"));
				CartItem ci = (CartItem) IGeneric.getInstance(rs.getBytes("cartItemObj"));
				order = new Orders(ac);
				order.addOrder(ci);
				return order;
			} else {
				return order;
			}
		}

	}

}