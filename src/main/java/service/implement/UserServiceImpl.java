package service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends service.interfaces.UserService {
	@Autowired
	dao.implement.UserDaoImpl userDao;
}