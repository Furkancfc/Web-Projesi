package dao.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import dao.implement.CartDaoImpl;
import dao.implement.OrdersDaoImpl;
import model.*;
import model.interfaces.IGeneric;

public abstract class UserDao extends Dao<Account> {
	@Override
	public Account create(Account c) {
		String sql = "insert into Account values ((?),(?),(?),(?),(?),(?),(?),(?))";
		return template.execute(sql, new PreparedStatementCallback<Account>() {
			@Override
			public Account doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, c.getUserName());
				ps.setString(2, c.getEmail());
				ps.setString(3, c.getPassword());
				ps.setString(4, c.getUserId());
				ps.setBytes(5, IGeneric.getBytes(c));
				ps.setBytes(6, IGeneric.getBytes(c.getCart()));
				ps.setBytes(7, IGeneric.getBytes(c.getOrders()));
				ps.setString(8, c.getAuth());
				ps.execute();
				return c;
			}
		});
	}

	@Override
	public Account update(Account c) {
		String sql = "update Account set username=(?), email=(?), pass=(?), userId=(?), cartId=(?), ordersId=(?), obj=(?),cart=(?),orders=(?),auth=(?) where userId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Account>() {
			@Override
			public Account doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, c.getUserName());
				ps.setString(2, c.getEmail());
				ps.setString(3, c.getPassword());
				ps.setString(4, c.getUserId());
				ps.setBytes(5, IGeneric.getBytes(c));
				ps.setBytes(6, IGeneric.getBytes(c.getCart()));
				ps.setBytes(7, IGeneric.getBytes(c.getOrders()));
				ps.setString(8, c.getAuth());
				ps.execute();
				return c;
			}
		});
	}

	@Override
	public void delete(String tId) {
		String sql = "delete from Account where userId=(?)";
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
	public void delete(Account t) {
		delete(t.getUserId());
	}

	public class UserMapper implements RowMapper<Account> {
		@Override
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			Account a = (Account) IGeneric.getInstance(rs.getBytes("obj"));
			if (a != null)
				return a;
			if (a == null && rs != null) {
				String username = rs.getString("username");
				String email = rs.getString("email");
				String pass = rs.getString("pass");
				String userId = rs.getString("userId");
				Cart c = (Cart) IGeneric.getInstance(rs.getBytes("cart"));
				Orders o = (Orders) IGeneric.getInstance(rs.getBytes("orders"));
				String auth = rs.getString("auth");
				a = new Account(email, pass, username, auth);
				a.setUserId(userId);
				a.setCart(c);
				a.setOrders(o);
				a.setAuth(auth);
				return a;
			}
			return null;
		}
	}
}
