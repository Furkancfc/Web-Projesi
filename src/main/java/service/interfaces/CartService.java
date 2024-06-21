package service.interfaces;

import model.*;

public abstract class CartService extends Service {

	public abstract void incrementCartItem(Cart c, CartItem ci);

	public abstract void decrementCartItem(Cart c, CartItem ci);

	public abstract void removeCartItem(Cart c, CartItem ci);

	public abstract void addCartItem(Cart c, CartItem ci);
}