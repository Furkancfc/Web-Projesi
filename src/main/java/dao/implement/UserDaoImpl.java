package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import dao.interfaces.UserDao;
import model.Account;
import model.interfaces.IGeneric;

@Repository
public class UserDaoImpl extends UserDao {
	public model.Account getUser(String email) {
		String sql = "select * from Account";
		template.query(sql, new UserMapper());
		return null;
	}

	public model.Account createUser(Account c) {
		String sql = "insert into Account values ((?),(?),(?),(?),(?),(?),(?),(?),(?),(?))";
		return template.execute(sql, new PreparedStatementCallback<Account>() {
			@Override
			public Account doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, c.getUserName());
				ps.setString(2, c.getEmail());
				ps.setString(3, c.getPassword());
				ps.setString(4, c.getUserId());
				ps.setString(5, c.getCartId());
				ps.setString(6, c.getOrdersId());
				ps.setBytes(7, IGeneric.getBytes(c));
				ps.setBytes(8, IGeneric.getBytes(c.getUserCart()));
				ps.setBytes(9, IGeneric.getBytes(c.getUserOrders()));
				ps.setString(10, c.getAuth());
				ps.execute();
				return c;
			}
		});
	}
//	public 

	@Override
	public List<Account> getAccounts() {
		String sql = "select * from Account";
		return template.query(sql, new UserMapper());
	}

	@Override
	public Account getAccount(Account c) {
		return getAccount(c.getUserId());
	}

	@Override
	public Account getAccount(String accountId) {
		String sql = "select * from Account where userId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Account>() {
			@Override
			@Nullable
			public Account doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, accountId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new UserMapper().mapRow(rs, 1);
				}
				return null;
			}
		});

	}

	@Override
	public Account addAccount(Account c) {
		return createUser(c);
	}

	@Override
	public Account deleteAccount(Account c) {
		String sql = String.format("delete from Account where userId=\"%s\"", c.getUserId());
		template.execute(sql);
		return c;
	}

	public void deleteAccount(String cId) {
		String sql = String.format("delete from Account where userId=\"%s\"", cId);
		template.execute(sql);
	}

	@Override
	public Account updateAccount(Account c) {
		String sql = "update Account set username=(?), email=(?), pass=(?), userId=(?), cartId=(?), ordersId=(?), obj=(?),cart=(?),orders=(?),auth=(?) where userId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Account>() {
			@Override
			public Account doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, c.getUserName());
				ps.setString(2, c.getEmail());
				ps.setString(3, c.getPassword());
				ps.setString(4, c.getUserId());
				ps.setString(5, c.getCartId());
				ps.setString(6, c.getOrdersId());
				ps.setBytes(7, IGeneric.getBytes(c));
				ps.setBytes(8, IGeneric.getBytes(c.getUserCart()));
				ps.setBytes(9, IGeneric.getBytes(c.getUserOrders()));
				ps.setString(10, c.getAuth());
				ps.setString(11, c.getUserId());
				ps.executeUpdate();
				return c;
			}
		});
	}

	@Override
	public Account display(Account t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account display(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Account t) {
		deleteAccount(t);
	}

	@Override
	public Account create(Account t) {
		return createUser(t);
	}

	@Override
	public Account update(Account t) {
		return updateAccount(t);
	}

	@Override
	public void delete(String tId) {
		deleteAccount(tId);
	}

}