package service.implement;

import model.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import model.CartItem;
import service.interfaces.ItemService;

@Service
public class CartServiceImpl extends service.interfaces.CartService {
	@Autowired
	private dao.implement.CartDaoImpl cartDao;
	@Autowired
	private dao.implement.ItemDaoImpl itemDao;
	@Autowired
	dao.implement.UserDaoImpl userDao;

	public CartItem getCartItem(String cartId, String cartItemId) {
		if (userDao.getAccount(cartId) != null) {
			Cart c = cartDao.getCart(cartId);
			if (c != null) {
				CartItem ci = c.getItem(cartItemId);
				if (ci != null) {
					return ci;
				}
			}
		}
		return null;
	}

	public Cart getCart(String cartId) {
		if (userDao.getAccount(cartId) != null) {
			Cart c = cartDao.getCart(cartId);
			if (c != null) {
				return c;
			} else {
				return cartDao.create(new Cart(cartId));
			}
		} else {
			return null;
		}
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
		CartItem ci = new CartItem(i.getItemId(), cartId);
		c.addItem(ci);
		cartDao.update(c);
	}

	public void addCartItem(String cartId, CartItem cartItem, int count) {
		Cart c = cartDao.getCart(cartId);
		c.setCount(cartId, count);
		c.addItem(cartItem);
		cartDao.update(c);
	}

	public void addCartItem(String cartId, String itemId, int count) {
		Cart c = cartDao.getCart(cartId);
		Item i = itemDao.getItem(itemId);
		CartItem ci = new CartItem(i.getItemId(), cartId);
		ci.setItemCount(count);
		c.addItem(ci);
		cartDao.update(c);
	}

	public Double calculateCartPrice(String cartId) {
		Cart c = cartDao.getCart(cartId);
		double totalPrice = 0d;
		for (CartItem x : c.getItems()) {
			Item i = itemDao.getItem(x.getItemId());
			if (i != null)
				totalPrice += i.getPrice() * x.getItemCount();
		}
		return totalPrice;
	}
}