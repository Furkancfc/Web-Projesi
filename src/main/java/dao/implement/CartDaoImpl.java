package dao.implement;

import model.Cart;
import model.CartItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.*;

import dao.interfaces.CartDao;

@Repository
public class CartDaoImpl extends CartDao {
	public CartItem getCartItem(String cId, String itemId) {
		String sql = "select * from Cart as c, CartItem as ci where c.cartId=(?) and ci.itemId=(?)";

		return template.execute(sql, new PreparedStatementCallback<CartItem>() {

			@Override
			public CartItem doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(0, cId);
				ps.setString(1, itemId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new CartItemMapper().mapRow(rs, rs.getRow());
				} else
					return null;
			}
		});
	}

	public CartItem getCartItem(Cart c, CartItem item) {
		return getCartItem(c.getCartId(), item.getItemId());
	}

	public List<Cart> getCarts() {
		String sql = "select * from Cart";
		return template.query(sql, new CartMapper());
	}

	public Cart getCart(Cart c) {
		return getCart(c.getCartId());
	}

	public Cart getCart(String cartId) {
		String sql = "select * from Cart where cartId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Cart>() {
			@Override
			@Nullable
			public Cart doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, cartId);
				ResultSet rs = ps.executeQuery();
				if (rs.getRow() > 0 || rs.next()) {
					return new CartMapper().mapRow(rs, 0);
				} else {
					return null;
				}
			}
		});
	}

	public List<CartItem> getCartItems(Cart c) {
		return getCartItems(c.getCartId());
	}

	public List<CartItem> getCartItems(String cartId) {
		String sql = "select * from Cart where cartId=(?)";
		return template.query(sql, (PreparedStatement ps) -> {
			ps.setString(1, cartId);
			ps.execute();
		}, new CartItemMapper());

	}
}