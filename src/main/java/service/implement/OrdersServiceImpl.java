package service.implement;

import org.springframework.beans.factory.annotation.Autowired;

import dao.implement.OrdersDaoImpl;
import dao.implement.UserDaoImpl;
import model.Orders;
import model.Account;
import model.Order;

public class OrdersServiceImpl extends service.interfaces.OrdersService {
	@Autowired
	private OrdersDaoImpl ordersDao;
	@Autowired
	private UserDaoImpl userDao;

	public Orders getOrders(String ordersId) {
		Account u = userDao.getAccount(ordersId);
		if (u != null) {
			Orders o = ordersDao.getOrders(ordersId);
			if (o != null)
				return o;
			else if (u.getOrders() != null) {
				ordersDao.create(u.getOrders());
				return u.getOrders();
			} else {
				return null;
			}
		}
		return null;
	}

	public void deleteOrders(String ordersId) {
		ordersDao.deleteOrders(ordersId);
	}

	public void deleteOrder(String ordersId, String orderId) {
		ordersDao.deleteOrder(ordersId, orderId);
	}

	public Orders createOrders(Orders orders) {
		return ordersDao.createOrders(orders);
	}

	public Orders addOrder(String ordersId, model.Order o) {
		Orders orders = getOrders(ordersId);
		return ordersDao.addOrder(orders, o);
	}

}