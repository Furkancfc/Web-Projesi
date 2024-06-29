package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.lang.Nullable;
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
		return create(c);
	}
	// public

	public List<Account> getAccounts() {
		String sql = "select * from Account";
		return template.query(sql, new UserMapper());
	}

	public Account getAccount(String accountId) {
		String sql = "select * from Account where userId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Account>() {
			@Override
			@Nullable
			public Account doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, accountId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new UserMapper().mapRow(rs, rs.getRow());
				}
				return null;
			}
		});
	}

	public Account getAccountForEmail(String email) {
		String sql = "select * from Account where email=(?)";
		return template.execute(sql, new PreparedStatementCallback<Account>() {
			@Override
			@Nullable
			public Account doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new UserMapper().mapRow(rs, rs.getRow());
				} else {
					return null;
				}
			}
		});
	}

	public Account addAccount(Account c) {
		return createUser(c);
	}

	public void deleteAccount(String accountId) {
		delete(accountId);
	}

	public Account updateAccount(Account c) {
		return update(c);
	}
}