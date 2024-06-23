package service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import model.*;

@Service
public class UserServiceImpl extends service.interfaces.UserService {
	@Autowired
	dao.implement.UserDaoImpl userDao;

	public Account createAccount(Account c) {
		return userDao.createUser(c);
	}

	public Account getAccount(Account c) {
		return userDao.getAccount(c);
	}

	public Account getAccount(String userId) {
		return userDao.getAccount(userId);
	}

	public Account getAccountForEmail(String email) {
		return userDao.getAccountForEmail(email);
	}

	public Account deleteAccount(Account c) {
		return userDao.deleteAccount(c);
	}

	public Account updateAccount(Account c) {
		return userDao.updateAccount(c);
	}
}