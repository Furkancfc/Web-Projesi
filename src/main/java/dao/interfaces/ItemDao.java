package dao.interfaces;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.http.Part;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import io.micrometer.common.lang.NonNull;
import model.Item;
import model.interfaces.IGeneric;

public abstract class ItemDao extends Dao<model.Item> {
	@Override
	public Item create(Item t) {
		String sql = "insert into Item values ((?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?))";
		return template.execute(sql, new PreparedStatementCallback<Item>() {
			@Override
			public Item doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, t.getItemId());
				ps.setString(2, t.getTitle());
				ps.setString(3, t.getShortDesc());
				ps.setString(4, t.getLongDesc());
				ps.setBytes(5, IGeneric.getBytes(t.getImageURLs()));
				ps.setLong(6, t.getCreateTime().toEpochMilli());
				ps.setLong(7, t.getLastUpdate().toEpochMilli());
				ps.setLong(8, t.getLastAccess().toEpochMilli());
				ps.setBytes(9, IGeneric.getBytes(t));
				ps.setString(10, t.getCategoryName());
				ps.setString(11, t.getPrice());
				ps.execute();
				return t;
			}
		});
	}

	@Override
	public void delete(Item t) {
		delete(t.getItemId());
	}

	@Override
	public void delete(String tId) {
		String sql = "delete from Item where itemId=(?)";
		template.execute(sql, new PreparedStatementCallback<Void>() {
			public Void doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, tId);
				ps.execute();
				return null;
			}
		});
	}

	@Override
	public Item update(Item t) {
		String sql = "UPDATE Item SET title=(?), shortDesc=(?), longDesc=(?), imageURIs=(?), createTime=(?), lastUpdate=(?), lastAccess=(?), obj=(?), categoryName=(?) WHERE itemId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Item>() {
			@Override
			public Item doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, t.getTitle());
				ps.setString(2, t.getShortDesc());
				ps.setString(3, t.getLongDesc());
				ps.setBytes(4, IGeneric.getBytes(t.getImageURLs()));
				ps.setLong(5, t.getCreateTime().toEpochMilli());
				ps.setLong(6, t.getLastUpdate().toEpochMilli());
				ps.setLong(7, t.getLastAccess().toEpochMilli());
				ps.setBytes(8, IGeneric.getBytes(t));
				ps.setString(9, t.getCategoryName());
				ps.setString(10, t.getItemId());
				return t;
			}
		});
	}

	public class ItemMapper implements RowMapper<model.Item> {
		@Override
		public Item mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
			Item i = (Item) IGeneric.getInstance(rs.getBytes("obj"));
			if (i != null) {
				return i;
			} else if (rs.getRow() > 0 || rs.next()) {
				String itemId = rs.getString("itemId");
				String title = rs.getString("title");
				String shortDesc = rs.getString("shortDesc");
				String longDesc = rs.getString("longDesc");
				List<Part> images = (List<Part>) IGeneric.getInstance(rs.getBytes("images"));
				if (images == null) {
					images = new ArrayList<Part>();
				}
				long createTime = rs.getLong("createTime");
				long lastUpdate = rs.getLong("lastUpdate");
				long lastAccess = rs.getLong("lastAccess");
				String categoryName = rs.getString("categoryName");
				String price = rs.getString("price");
				i = new Item(title, shortDesc, longDesc, categoryName, images, price);
				update(i);
				return i;
			} else {
				return null;
			}
		}
	}
}