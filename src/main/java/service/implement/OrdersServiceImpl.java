package service.implement;

import org.springframework.beans.factory.annotation.Autowired;

import dao.implement.OrdersDaoImpl;
import model.Orders;
import model.Orders.Order;

public class OrdersServiceImpl extends service.interfaces.OrdersService {
	@Autowired
	private OrdersDaoImpl ordersDao;

	@Override
	public Orders getOrders(String ordersId) {
		return ordersDao.getOrders(ordersId);
	}

	@Override
	public void deleteOrders(String ordersId) {
		ordersDao.deleteOrders(ordersId);
	}

	@Override
	public void deleteOrder(String ordersId, String orderId) {
		ordersDao.deleteOrder(ordersId, orderId);
	}

	@Override
	public Orders createOrders(Orders orders) {
		return ordersDao.createOrders(orders);
	}

	@Override
	public Orders addOrder(String ordersId, Order order) {
		return ordersDao.addOrder(ordersId, order);
	}

}