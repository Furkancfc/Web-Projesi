package dao.implement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

import model.Orders;
import model.interfaces.IGeneric;

public class OrdersDaoImpl extends dao.interfaces.OrderDao {
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

	@Override
	public Orders create(Orders t) {
		String sql = "insert into Orders values ((?),(?),(?),(?))";
		return template.execute(sql, new PreparedStatementCallback<Orders>() {
			@Override
			public Orders doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, t.getOrdersId());
				ps.setBytes(2, IGeneric.getBytes(t.getAccount()));
				ps.setBytes(3,IGeneric.getBytes(t.getOrders()));
				return t;
			}
		});
	}

	@Override
	public void delete(Orders t) {
		delete(t.getAccountId());
	}

	@Override
	public void delete(String tId) {
		String sql = String.format("delete from Orders where ordersId=\"%s\"", tId);
		template.execute(sql);

	}

	@Override
	public Orders update(Orders t) {
		String sql = "update Orders set accountObj=(?), ordersList=(?), obj=(?) where ordersId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Orders>() {
			@Override
			public Orders doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setBytes(1, IGeneric.getBytes(t.getAccount()));
				ps.setBytes(2, IGeneric.getBytes(t.getOrders()));
				ps.setBytes(3,IGeneric.getBytes(t));
				ps.setString(4, t.getOrdersId());
				return t;
			}
		});
	}

}