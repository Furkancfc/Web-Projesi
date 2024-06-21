package dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dao.interfaces.CategoryDao.CategoryMapper;
import model.*;
import model.interfaces.*;

public abstract class CartDao extends Dao<Cart> {

	public abstract void addCartItem(model.Cart c, model.CartItem ci);

	public abstract void removeCartItem(model.Cart c, model.CartItem ci);

	public abstract void incrementCartItem(model.Cart c, model.CartItem ci);

	public abstract void decrementCartItem(model.Cart c, model.CartItem ci);

	public abstract CartItem getCartItem(model.Cart c, String itemId);

	public abstract CartItem getCartItem(model.Cart c, model.CartItem item);

	public abstract Map<String, CartItem> getCartItems(model.Cart c);

	public abstract void getCart(model.Cart c);

	public abstract void getCart(String cartId);

	public class CartItemRowMapper implements RowMapper<CartItem> {
		@Override
		public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			CartItem ci;
			if (rowNum >= 0) {
				ci = (CartItem) IGeneric.getInstance(rs.getBytes("obj"));
				if (ci != null) {
					return ci;
				} else {
					long listingTime = rs.getLong("listingTime");
					String itemId = rs.getString("itemId");
					short itemCount = rs.getShort("itemCount");
					Item item = (Item) IGeneric.getInstance(rs.getBytes("item"));
					ci = new CartItem(item);
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

	public class CartRowMapper implements RowMapper<Cart> {
		@Override
		public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cart c = (Cart) model.interfaces.IGeneric.getInstance(rs.getBytes("obj"));
			if (c != null) {
				return c;
			} else {
				return null;
			}
		}
	}

}