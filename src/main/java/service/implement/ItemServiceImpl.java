package service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.interfaces.ItemDao;
import model.Item;

@Service
public class ItemServiceImpl extends service.interfaces.ItemService {
	@Autowired
	public dao.implement.ItemDaoImpl itemDao;

	public Item addItem(Item t) {
		return itemDao.create(t);
	}

	public void removeItem(Item t) {
		itemDao.delete(t);
	}

	public Item updateItem(Item t) {
		return itemDao.update(t);
	}
	public List<Item> getItems(){
		return itemDao.getItems();
	}
	public Item getItem(String itemId){
		return itemDao.getItem(itemId);
	}
}