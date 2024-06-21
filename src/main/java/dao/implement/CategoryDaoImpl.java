package dao.implement;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.*;

import dao.interfaces.CategoryDao;
import model.Category;
import model.Item;
import model.interfaces.IGeneric;

@Repository
public class CategoryDaoImpl extends CategoryDao {
	public List<Category> getCategories() {
		String sql = "select * from Category";
		return template.query(sql, new CategoryMapper());
	}

	public Category getCategory(Category c) {
		String sql = "select * from categoryId=(?)";
		return template.execute(sql, new PreparedStatementCallback<Category>() {
			@Override
			public Category doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, c.getCategoryId());
				ResultSet rs = ps.executeQuery();
				return new CategoryMapper().mapRow(rs, rs.getRow());
			}
		});
	}

	public Category getCategory(String c) {
		String sql = "select * from name=(?)";
		return template.execute(sql, new PreparedStatementCallback<Category>() {
			@Override
			public Category doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, c);
				ResultSet rs = ps.executeQuery();
				return new CategoryMapper().mapRow(rs, rs.getRow());
			}
		});
	}

	@Override
	public Category addCategory(model.Category object) {
		System.out.println("Adding Category to DB");
		String sql = "insert into Category values ((?),(?),(?),(?),(?))";
		template.execute(sql, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, object.getName());
				ps.setString(2, object.getCategoryId());
				ps.setInt(3, object.getItemCount());
				ps.setBytes(4, IGeneric.getBytes(object.getItems()));
				ps.setBytes(5, IGeneric.getBytes(object));
				return ps.execute();
			}
		});
		return object;
	}

	@Override
	public void deleteCategory(model.Category object) {
		String sql = "delete from Category where name=(?)";
		template.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, object.getName());
				return ps.execute();
			}
		});
	}

	public void deleteCategory(String objectId) {
		String sql = "delete from Category where name=(?)";
		template.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, objectId);
				return ps.execute();
			}
		});
	}

	@Override
	public Category updateCategory(model.Category object) {
		String sql = "update Category where name=(?)";
		template.update(sql);
		return object;
	}

	@Override
	public Category display(Category t) {
		return null;
	}

	@Override
	public Category display(String id) {

		return null;
	}

	@Override
	public void delete(Category t) {
		deleteCategory(t);
	}

	@Override
	public Category create(Category t) {
		return addCategory(t);
	}

	@Override
	public Category update(Category t) {
		return updateCategory(t);
	}

	@Override
	public void delete(String tId) {
		deleteCategory(tId);
	}

}