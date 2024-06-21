package dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Item;
import model.interfaces.IGeneric;

public abstract class ItemDao extends Dao<model.Item> {
	public class ItemMapper implements RowMapper<model.Item> {
		@Override
		public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
			Item i = (Item) IGeneric.getInstance(rs.getBytes("obj"));
			if (i != null) {
				return i;
			}
			if (i == null && rs != null) {
				String itemId = rs.getString("itemId");
				String title = rs.getString("title");
				String shortDesc = rs.getString("shortDesc");
				String longDesc = rs.getString("longDesc");
				String imageUris[] = (String[]) IGeneric.getInstance(rs.getBytes("imageURIs"));
				long createTime = rs.getLong("createTime");
				long lastUpdate = rs.getLong("lastUpdate");
				long lastAccess = rs.getLong("lastAccess");
				String categoryName = rs.getString("categoryName");
				String price = rs.getString("price");
				i = new Item(title, shortDesc, longDesc, categoryName,price);
				return i;
			} else {
				return null;
			}
		}
	}
}