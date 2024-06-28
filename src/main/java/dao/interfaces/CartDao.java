package dao.interfaces;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;


import dao.interfaces.CategoryDao.CategoryMapper;
import io.micrometer.common.lang.NonNull;
import model.*;
import model.interfaces.*;

public abstract class CartDao extends Dao<Cart> {
	@Override
	public Cart create(Cart c) {
		String sql = "insert into Cart values ((?),(?),(?),(?))";
		return template.execute(sql, new PreparedStatementCallback<Cart>() {
			@Override
			public Cart doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(1, c.getCartId());
					ps.setBytes(2, IGeneric.getBytes(c.getItems()));
					ps.setBigDecimal(3, new BigDecimal(c.getLastUpdate().toEpochMilli()));
					ps.setBytes(4, IGeneric.getBytes(c));
					ps.execute();
					return c;
				} catch (Exception e) {
					return null;
				}
			};

		});
	}

	@Override
	public Cart update(Cart c) {
		String sql = "update Cart set cartId=(?),items=(?),lastUpdate=(?),obj=(?) where cartId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Cart>() {
			@Override
			public Cart doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(1, c.getCartId());
					ps.setBytes(2, IGeneric.getBytes(c.getItems()));
					ps.setBigDecimal(3, new BigDecimal(c.getLastUpdate().toEpochMilli()));
					ps.setBytes(4, IGeneric.getBytes(c));
					ps.setString(5, c.getCartId());
					ps.execute();
					return c;
				} catch (Exception e) {
					return null;
				}
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
					ps.setString(1, tId);
					ps.execute();
				} catch (Exception e) {
				}
				return null;
			}
		});
	}

	@Override
	public void delete(Cart c) {
		delete(c.getCartId());
	}

	public class CartMapper implements RowMapper<Cart> {
		@Override
		public Cart mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
			Cart c = (Cart) model.interfaces.IGeneric.getInstance(rs.getBytes("obj"));
			if(c != null){
				return c;
			}
			else if (rs.getRow() > 0 || rs.next()) {
				String cartId = rs.getString("cartId");
				Map<String, CartItem> items = (Map<String, CartItem>) IGeneric.getInstance(rs.getBytes("items"));
				if(items == null){
					items = new TreeMap<String,CartItem>();
				}
				long lastUpdate = rs.getLong("lastUpdate");
				c.setCartId(cartId);
				c.setItems(items);
				c.setLastUpdate(Instant.ofEpochMilli(lastUpdate));
				update(c);
				return c;
			} else {
				return null;
			}
		}
	}

	public class CartItemMapper implements RowMapper<CartItem> {
		@Override
		public CartItem mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
			CartItem ci;
			if (rowNum >= 0) {
				ci = (CartItem) IGeneric.getInstance(rs.getBytes("obj"));
				if (ci != null) {
					return ci;
				} else {
					String cartId = rs.getString("cartId");
					String itemId = rs.getString("itemId");
					long listingTime = rs.getLong("listingTime");
					Item item = (Item) IGeneric.getInstance(rs.getBytes("item"));
					short itemCount = rs.getShort("itemCount");
					ci = new CartItem(item, cartId);
					ci.setCartItemId(itemId);
					ci.setItemCount(itemCount);
					ci.setListingTime(Instant.ofEpochMilli(listingTime));
					ci.setItemId(itemId);
					return ci;
				}
			} else {
				return null;
			}
		}
	}
}