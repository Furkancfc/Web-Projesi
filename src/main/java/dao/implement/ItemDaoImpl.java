package dao.implement;

import java.util.List;

import org.springframework.stereotype.Repository;

import dao.interfaces.ItemDao;
import model.Item;

@Repository
public class ItemDaoImpl extends ItemDao {

	public List<Item> getItems() {
		String sql = "select * from Item";
		return template.query(sql, new ItemMapper());
	}
	public Item getItem(String itemId){
		String sql = "select * from Item where itemId=(?)";
		return template.queryForObject(sql,new ItemMapper(),new Object[]{itemId});
	}
}