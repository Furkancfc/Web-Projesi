package dao.implement;

import java.sql.PreparedStatement;
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
		String sql = String.format("select * from Account where userId=\"%s\"", accountId);
		return template.queryForObject(sql, new UserMapper());

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