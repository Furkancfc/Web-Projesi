package service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements service.interfaces.ItemService {
	@Autowired
	public dao.implement.ItemDaoImpl itemDao;

}