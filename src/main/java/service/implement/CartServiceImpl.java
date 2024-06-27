package service.implement;

import model.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import model.CartItem;

@Service
public class CartServiceImpl extends service.interfaces.CartService {
	@Autowired
	private dao.implement.CartDaoImpl cartDao;
	@Autowired
	private dao.implement.ItemDaoImpl itemDao;

	public Cart getCart(String cartId) {
		return cartDao.getCart(cartId);
	}

	public List<Cart> getCarts() {
		return cartDao.getCarts();
	}

	public void incrementCartItem(String cartId, String cartItemId) {
		Cart c = cartDao.getCart(cartId);
		c.incrementItem(cartItemId);
		cartDao.update(c);
	}

	public void decrementCartItem(String cartId, String cartItemId) {
		Cart c = cartDao.getCart(cartId);
		c.decrementItem(cartItemId);
		cartDao.update(c);
	}

	public void removeCartItem(String cartId, String cartItemId) {
		Cart c = cartDao.getCart(cartId);
		c.deleteItem(cartItemId);
		cartDao.update(c);
	}

	public void addCartItem(String cartId, CartItem cartItem) {
		Cart c = cartDao.getCart(cartId);
		c.addItem(cartItem);
		cartDao.update(c);
	}

	public void addCartItem(String cartId, String itemId) {
		Cart c = cartDao.getCart(cartId);
		Item i = itemDao.getItem(itemId);
		CartItem ci = new CartItem(i, cartId);
		c.addItem(ci);
		cartDao.update(c);
	}
}