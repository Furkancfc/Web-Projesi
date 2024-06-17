package dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import model.Category;
import model.Item;

public interface CategoryDao extends dao.Dao<model.Category> {

	public void addCategory(model.Category category);

	public class CategoryMapper implements RowMapper<Category> {
	
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			try {
				if (rs != null) {
					Category c = null;
					if ((c = (Category) rs.getObject("obj")) != null) {
						return c;
					} else {
						c = new Category(null);
						c.setName(rs.getString("name"));
						c.setCategoryId(rs.getString("categoryId"));
						c.setItemIds((Map<String, Item>) rs.getObject("itemIds"));
						c.setItemCount(rs.getInt("itemCount"));
						return c;
					}
				} else {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
		}
	}

}