package dao.implement;

import model.Cart;
import com.mysql.cj.jdbc.Blob;
import model.CartItem;
import model.Item;
import model.interfaces.IGeneric;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.*;

import com.mysql.cj.protocol.a.BlobValueEncoder;

import dao.interfaces.CartDao;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Repository
public class CartDaoImpl implements CartDao {
	public JdbcTemplate getTemplate() {
		return template;
	}

	@Autowired
	public JdbcTemplate setTemplate(JdbcTemplate template) {
		this.template = template;
		return this.template;
	}

	@Autowired
	public void setDataSource(DriverManagerDataSource ds) {
		template.setDataSource(ds);
	}

	private String userId;
	@Autowired
	private JdbcTemplate template;

	@Override
	public Cart create(Cart c) {
		String sql = "insert into Cart values ((?),(?),(?),(?))";
		return template.execute(sql, new PreparedStatementCallback<Cart>() {
			@Override
			public Cart doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(0, c.getCartId());
					ps.setBlob(1, new Blob(IGeneric.getBytes(c.getItems()), null));
					ps.setBigDecimal(2, new BigDecimal(c.getLastUpdate().toEpochMilli()));
					ps.setBlob(3, new Blob(IGeneric.getBytes(c), null));
					return c;
				} catch (Exception e) {
					return null;
				}
			};

		});
	}

	@Override
	public Cart delete(Cart c) {
		String sql = "delete from Cart where cartId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Cart>() {
			@Override
			public Cart doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(0, c.getCartId());
					return c;
				} catch (Exception e) {
					return null;
				}
			}
		});
	}

	public Cart update(Cart c) {
		String sql = "update Cart set cartId=(?),items=(?),lastUpdate=(?),obj=(?) where cartId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Cart>() {
			@Override
			public Cart doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(0, c.getCartId());
					ps.setBlob(1, new Blob(IGeneric.getBytes(c.getItems()), null));
					ps.setBigDecimal(2, new BigDecimal(c.getLastUpdate().toEpochMilli()));
					ps.setBlob(3, new Blob(IGeneric.getBytes(c), null));
					ps.setString(4, c.getCartId());
					return c;
				} catch (Exception e) {
					return null;
				}
			}
		});
	}

	@Override
	public void addCartItem(Cart c, CartItem ci) {
		c.addItem(ci);
		update(c);
	}

	@Override
	public void removeCartItem(Cart c, CartItem ci) {
		c.deleteItem(ci.getItemId());
		update(c);

	}

	@Override
	public void incrementCartItem(Cart c, CartItem ci) {
		c.incrementItem(ci.getItemId());
		update(c);
	}

	@Override
	public void decrementCartItem(Cart c, CartItem ci) {
		c.decrementItem(ci.getItemId());
		String sql = "update Cart where cartId=((?),(?),(?),(?))";
		template.update(sql, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(0, ci.getItemId());
					ps.setBigDecimal(0, new BigDecimal(ci.getListingTime().toEpochMilli()));
					ps.setInt(2, ci.getItemCount());
					ps.setBlob(3, new Blob(IGeneric.getBytes(ci.getItem()), null));
					ps.setBlob(4, new Blob(IGeneric.getBytes(ci), null));
					return 0;

				} catch (Exception e) {
					return 1;
				}
			}

		});
	}

	@Override
	public Cart display(String cartId) {
		String sql = "select * from Cart where cartId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Cart>() {
			@Override
			public Cart doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(0, cartId);
				return null;

			}
		});
	}

	@Override
	public Cart display(Cart c) {
		String sql = "select * from Cart where cartId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Cart>() {
			@Override
			public Cart doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(0, c.getCartId());
				return null;
			}
		});
	}

	@Override
	public List<Cart> display() {
		String sql = "select * from Cart";
		return template.query(sql, new CartRowMapper());
	}
}