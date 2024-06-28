package dao.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import io.micrometer.common.lang.NonNull;
import model.Account;
import model.CartItem;
import model.Orders;
import model.Orders.Order;
import model.interfaces.IGeneric;

public abstract class OrdersDao extends Dao<model.Orders> {
	@Override
	public Orders create(Orders t) {
		String sql = "insert into Orders values ((?),(?),(?))";
		return template.execute(sql, new PreparedStatementCallback<Orders>() {
			@Override
			public Orders doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(1, t.getOrdersId());
					ps.setBytes(2, IGeneric.getBytes(t.getOrders()));
					ps.setBytes(3, IGeneric.getBytes(t));
					ps.execute();
					return t;
				} catch (Exception e) {
					return null;
				}
			}
		});
	}

	@Override
	public void delete(Orders t) {
		delete(t.getOrdersId());

	}

	@Override
	public void delete(String tId) {
		String sql = "delete from Orders where orderId=(?)";
		template.execute(sql, new PreparedStatementCallback<Void>() {
			@Override
			@Nullable
			public Void doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, tId);
				ps.execute();
				return null;
			}
		});
	}

	@Override
	public Orders update(Orders t) {
		String sql = "update Orders set ordersId=(?), ordersList=(?), obj=(?) where ordersId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Orders>() {
			@Override
			public Orders doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, t.getOrdersId());
				ps.setBytes(2, IGeneric.getBytes(t.getOrders()));
				ps.setBytes(3, IGeneric.getBytes(t));
				ps.setString(4, t.getOrdersId());
				ps.executeUpdate();
				return t;
			}
		});
	}

	public class OrderMapper implements RowMapper<model.Orders> {

		@Override
		public Orders mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
			Orders order = (Orders) IGeneric.getInstance(rs.getBytes("obj"));
			if (order != null) {
				return order;
			} else if (rs.getRow() > 0 || rs.next()) {
				String ordersId = rs.getString("ordersId");
				String userId = rs.getString("userId");
				Map<String, Order> orders = (Map<String, Order>) IGeneric.getInstance(rs.getBytes("ordersList"));
				if(orders == null){
					orders = new TreeMap<String,Orders.Order>();
				}
				order = new Orders(userId);
				order.setOrders(orders);
				order.setOrdersId(ordersId);
				update(order);
				return order;
			} else {
				return order;
			}
		}

	}

}