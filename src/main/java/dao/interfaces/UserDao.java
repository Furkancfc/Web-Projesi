package dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import model.*;
import model.interfaces.IGeneric;

public abstract class UserDao extends Dao<Account> {
	public abstract List<Account> getAccounts();

	public abstract Account getAccount(Account c);

	public abstract Account getAccount(String accountId);

	public abstract Account addAccount(Account c);

	public abstract Account deleteAccount(Account c);

	public abstract Account updateAccount(Account c);

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
				String cartId = rs.getString("cartId");
				Cart c = (Cart) IGeneric.getInstance(rs.getBytes("cart"));
				String auth = rs.getString("auth");
				c.setCartId(cartId);
				a = new Account(email, pass, username, auth);
				a.setUserCart(c);
				a.setUserId(userId);
				a.setAuth(auth);
				return a;
			}
			return null;
		}
	}
}
