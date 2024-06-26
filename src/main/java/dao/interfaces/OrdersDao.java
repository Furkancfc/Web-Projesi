package dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import model.Account;
import model.CartItem;
import model.Orders;
import model.Orders.Order;
import model.interfaces.IGeneric;

public abstract class OrdersDao extends Dao<model.Orders> {

	public class OrderMapper implements RowMapper<model.Orders> {

		@Override
		public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
			Orders order = (Orders) IGeneric.getInstance(rs.getBytes("obj"));
			if (order == null) {
				String ordersId = rs.getString("ordersId");
				String userId = rs.getString("userId");
				Map<String, Order> orders = (Map<String, Order>) IGeneric.getInstance(rs.getBytes("ordersList"));
				order = new Orders(userId);
				order.setOrders(orders);
				order.setOrdersId(ordersId);
				return order;
			} else {
				return order;
			}
		}

	}

}