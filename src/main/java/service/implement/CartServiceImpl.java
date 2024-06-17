package service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import model.CartItem;

@Service
public class CartServiceImpl implements service.interfaces.CartService {
	@Autowired
	private dao.implement.CartDaoImpl cartDao;

	public void incrementCartItem(model.CartItem ci) {
		cartDao.incrementCartItem(ci);
	}

	public void decrementCartItem(model.CartItem ci) {
		cartDao.decrementCartItem(ci);
	}

	@Override
	public void removeCartItem(model.CartItem ci) {
		cartDao.removeCartItem(ci);
	}

	@Override
	public void addCartItem(model.CartItem ci) {
		cartDao.addCartItem(ci);
	}
}