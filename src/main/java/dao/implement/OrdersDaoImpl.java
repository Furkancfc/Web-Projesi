package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.lang.Nullable;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import model.CartItem;
import model.Orders;
import model.interfaces.IGeneric;

public class OrdersDaoImpl extends dao.interfaces.OrdersDao {

	public Orders deleteOrder(String ordersId, String orderId) {
		Orders o = getOrders(orderId);
		o.removeOrder(orderId);
		return update(o);
	}

	public Orders addOrder(Orders o, model.Order order) {
		if (o == null)
			return null;
		o.addOrder(order);
		return update(o);
	}

	public Orders addOrder(String ordersId, CartItem ci, Double price, String payment_Method) {
		Orders o = getOrders(ordersId);
		o.addOrder(ci, price, payment_Method);
		return update(o);
	}

	public Orders getOrders(String ordersId) {
		String sql = "select * from Orders where ordersId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Orders>() {
			public Orders doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, ordersId);
				ResultSet rs = ps.executeQuery();
				if (rs.getRow() > 0 || rs.next()) {
					return new OrdersMapper().mapRow(rs, rs.getRow());
				} else
					return null;
			}
		});
	}

	public Orders createOrders(Orders t) {
		return create(t);
	}

	public void deleteOrders(Orders t) {
		deleteOrders(t.getOrdersId());
	}

	public void deleteOrders(String ordersId) {
		delete(ordersId);
	}

	public model.Orders updateOrders(Orders t) {
		return update(t);
	}
}