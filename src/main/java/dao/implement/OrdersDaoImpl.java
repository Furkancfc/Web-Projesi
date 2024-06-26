package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.lang.Nullable;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import model.Orders;
import model.interfaces.IGeneric;

public class OrdersDaoImpl extends dao.interfaces.OrdersDao {
	@Override
	public Orders display(Orders t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders display(String tId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Orders deleteOrder(String ordersId, String orderId) {
		Orders o = getOrders(orderId);
		o.removeOrder(orderId);
		return o;
	}

	public Orders addOrder(String ordersId, model.Orders.Order order) {
		Orders o = getOrders(ordersId);
		o.setOrder(order);
		return updateOrders(o);
	}

	public Orders getOrders(String ordersId) {
		String sql = "select * from Orders where ordersId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Orders>() {
			public Orders doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, ordersId);
				ResultSet rs = ps.executeQuery();
				return new OrderMapper().mapRow(rs, rs.getRow());
			}
		});
	}

	public Orders createOrders(Orders t) {
		String sql = "insert into Orders values ((?),(?),(?),(?))";
		return template.execute(sql, new PreparedStatementCallback<Orders>() {
			@Override
			public Orders doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(1, t.getOrdersId());
					ps.setString(2, t.getUserId());
					ps.setBytes(3, IGeneric.getBytes(t.getOrders()));
					ps.setBytes(4, IGeneric.getBytes(t));
					ps.execute();
					return t;
				} catch (Exception e) {
					return null;
				}
			}
		});
	}

	public void deleteOrders(Orders t) {
		deleteOrders(t.getOrdersId());
	}

	public void deleteOrders(String ordersId) {
		String sql = "delete from Orders where ordersId=(?)";
		template.execute(sql, new PreparedStatementCallback<Void>() {
			@Override
			@Nullable
			public Void doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, ordersId);
				ps.execute();
				return null;
			}
		});

	}

	public model.Orders updateOrders(Orders t) {
		String sql = "update Orders set ordersId=(?), userId=(?), ordersList=(?), obj=(?) where ordersId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Orders>() {
			@Override
			public Orders doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, t.getOrdersId());
				ps.setString(2, t.getUserId());
				ps.setBytes(3, IGeneric.getBytes(t.getOrders()));
				ps.setBytes(4, IGeneric.getBytes(t));
				return t;
			}
		});
	}

	@Override
	public Orders create(Orders t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Orders t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String tId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Orders update(Orders t) {
		// TODO Auto-generated method stub
		return null;
	}

}