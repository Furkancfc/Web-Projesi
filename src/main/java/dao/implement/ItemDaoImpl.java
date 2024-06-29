package dao.implement;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import dao.interfaces.ItemDao;
import model.Item;

@Repository
public class ItemDaoImpl extends ItemDao {

	public List<Item> getItems() {
		String sql = "select * from Item";
		return template.query(sql, new ItemMapper());
	}

	public Item getItem(String itemId) {
		String sql = "select * from Item where itemId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Item>() {
			@Override
			@Nullable
			public Item doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, itemId);
				ResultSet rs = ps.executeQuery();
				if (rs.getRow() > 0 || rs.next()) {
					return new ItemMapper().mapRow(rs, rs.getRow());
				}
				return null;
			}
		});
	}
}