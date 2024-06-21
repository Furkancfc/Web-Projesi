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
import java.util.Map;

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
public class CartDaoImpl extends CartDao {
	private String userId;

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
					ps.execute();
					return c;
				} catch (Exception e) {
					return null;
				}
			};

		});
	}

	@Override
	public void delete(Cart c) {
		delete(c.getCartId());
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
					ps.execute();
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
					ps.execute();
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
				ps.execute();
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
				ps.execute();
				return null;
			}
		});
	}

	@Override
	public CartItem getCartItem(Cart c, String itemId) {
		String sql = "select * from Cart as c, CartItem as ci where c.cartId=(?) and ci.itemId=(?)";

		return template.execute(sql, new PreparedStatementCallback<CartItem>() {

			@Override
			public CartItem doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(0, c.getCartId());
				ps.setString(1, itemId);
				ResultSet rs = ps.executeQuery();
				return new CartItemRowMapper().mapRow(rs, rs.getRow());
			}
		});

	}

	@Override
	public CartItem getCartItem(Cart c, CartItem item) {
		return getCartItem(c, item.getItemId());
	}

	@Override
	public void getCart(Cart c) {
		getCart(c.getCartId());
	}

	@Override
	public void getCart(String cartId) {
		String sql = String.format("select * from Cart where cartId=\"%s\"", cartId);
		template.queryForObject(sql, new CartRowMapper());
	}

	@Override
	public Map<String, CartItem> getCartItems(Cart c) {
		String sql = "select * from Cart where cartId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Map<String, CartItem>>() {
			@Override
			public Map<String, CartItem> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				ps.setString(1, c.getCartId());
				ResultSet rs = ps.executeQuery();
				return new CartRowMapper().mapRow(rs, rs.getRow()).getItems();
			}
		});
	}

	@Override
	public void delete(String tId) {
		String sql = "delete from Cart where cartId=(?)";
		template.execute(sql, new PreparedStatementCallback<Cart>() {
			@Override
			public Cart doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(0, tId);
					ps.execute();
				} catch (Exception e) {
				}
				return null;
			}
		});
	}

}