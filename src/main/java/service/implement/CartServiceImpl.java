package service.implement;
import model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import model.CartItem;

@Service
public class CartServiceImpl extends service.interfaces.CartService {
	@Autowired
	public dao.implement.CartDaoImpl cartDao;

	public void incrementCartItem(Cart c, CartItem ci) {
		cartDao.incrementCartItem(c,ci);
	}

	public void decrementCartItem(Cart c,CartItem ci) {
		cartDao.decrementCartItem(c,ci);
	}

	@Override
	public void removeCartItem(Cart c,CartItem ci) {
		cartDao.removeCartItem(c,ci);
	}

	@Override
	public void addCartItem(Cart c,CartItem ci) {
		cartDao.addCartItem(c,ci);
	}
}