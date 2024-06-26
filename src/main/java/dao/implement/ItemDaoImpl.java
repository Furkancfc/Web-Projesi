package dao.implement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import dao.interfaces.ItemDao;
import model.Item;
import model.interfaces.IGeneric;

@Repository
public class ItemDaoImpl extends ItemDao {

	@Override
	public Item display(Item t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item display(String tId) {
		// TODO Auto-generated method stub
		return null;
	}

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
		String sql = String.format("delete from Item where itemId=\"%s\"", tId);
		template.execute(sql);

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

	public List<Item> getItems() {
		String sql = "select * from Item";
		return template.query(sql, new ItemMapper());
	}

}