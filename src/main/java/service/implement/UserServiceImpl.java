package service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements service.interfaces.UserService {
	@Autowired
	dao.implement.UserDaoImpl userDao;
}