package service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Account;

@Service
public class UserServiceImpl extends service.interfaces.UserService {
	@Autowired
	dao.implement.UserDaoImpl userDao;
	@Autowired
	dao.implement.CartDaoImpl cartDao;
	@Autowired
	dao.implement.OrdersDaoImpl ordersDao;

	public Account createUser(Account account) {
		cartDao.create(account.getCart());
		ordersDao.create(account.getOrders());
		return userDao.create(account);
	}

	public Account getUser(String userId) {
		return userDao.getAccount(userId);
	}

	public List<Account> getUsers() {
		return userDao.getAccounts();
	}

	public void deleteUser(String userId) {
		userDao.delete(userId);
		cartDao.delete(userId);
		ordersDao.delete(userId);
	}

	public Account updateUser(Account account) {
		cartDao.update(account.getCart());
		ordersDao.update(account.getOrders());
		return userDao.update(account);
	}

}