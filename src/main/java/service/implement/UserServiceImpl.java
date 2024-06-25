package service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Account;

@Service
public class UserServiceImpl extends service.interfaces.UserService {
	@Autowired
	dao.implement.UserDaoImpl userDao;

	public Account createUser(Account account) {
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
	}

	public Account updateUser(Account account) {
		return userDao.update(account);
	}

}