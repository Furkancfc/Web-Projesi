package service.interfaces;

public abstract class OrdersService extends Service {

	public abstract void deleteOrder(String ordersId, String orderId);

	public abstract model.Orders addOrder(String ordersId, model.Orders.Order order);

	public abstract model.Orders getOrders(String ordersId);

	public abstract model.Orders createOrders(model.Orders orders);

	public abstract void deleteOrders(String ordersId);
}