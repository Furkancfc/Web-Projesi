package service.interfaces;

public interface CartService {
	public void incrementCartItem(model.CartItem ci);

	public void decrementCartItem(model.CartItem ci);

	public void removeCartItem(model.CartItem ci);

	public void addCartItem(model.CartItem ci);
}