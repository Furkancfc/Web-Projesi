package dao.interfaces;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.jdbc.core.RowMapper;

import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.protocol.a.BlobValueEncoder;

import model.Category;
import model.Item;
import model.interfaces.IGeneric;

public abstract class CategoryDao extends Dao<Category> {

	public abstract Category addCategory(model.Category category);

	public abstract void deleteCategory(model.Category category);

	public abstract Category updateCategory(model.Category category);

	public class CategoryMapper implements RowMapper<Category> {
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			try {
				if (rs != null) {
					Category c = null;
					if ((c = (Category) IGeneric.getInstance(rs.getBytes("obj"))) != null) {
						return c;
					} else {
						c = new Category(null);
						c.setName(rs.getString("name"));
						c.setCategoryId(rs.getString("categoryId"));
						c.setItemIds((Map<String, Item>) IGeneric.getInstance(rs.getBytes("itemIds")));
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