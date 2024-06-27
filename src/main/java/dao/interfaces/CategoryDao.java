package dao.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import model.Category;
import model.Item;
import model.interfaces.IGeneric;

public abstract class CategoryDao extends Dao<Category> {

	@Override
	public void delete(Category t) {
		delete(t.getCategoryId());
	}

	@Override
	public Category create(Category t) {
		String sql = "insert into Category values ((?),(?),(?),(?),(?))";
		return template.execute(sql, new PreparedStatementCallback<Category>() {

			@Override
			public Category doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, t.getName());
				ps.setString(2, t.getCategoryId());
				ps.setInt(3, t.getItemCount());
				ps.setBytes(4, IGeneric.getBytes(t.getItems()));
				ps.setBytes(5, IGeneric.getBytes(t));
				ps.execute();
				return t;
			}
		});
	}

	@Override
	public Category update(Category t) {
		String sql = "update Category where name=(?)";
		return template.execute(sql, new PreparedStatementCallback<Category>() {
			@Override
			@Nullable
			public Category doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, t.getName());
				ps.executeUpdate();
				return t;
			}
		});
	}

	@Override
	public void delete(String tId) {
		String sql = "delete from Category where name=(?)";
		template.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, tId);
				return ps.execute();
			}
		});
	}
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
						c.setItemCount(rs.getInt("itemCount"));
						c.setItemIds((Map<String, Item>) IGeneric.getInstance(rs.getBytes("items")));
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