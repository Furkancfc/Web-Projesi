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

@Repository
public class CategoryDaoImpl implements CategoryDao {
	@Autowired
	public void setDataSource(DriverManagerDataSource ds) {
		template.setDataSource(ds);
	}

	@Autowired
	private JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	@Autowired
	public JdbcTemplate setTemplate(JdbcTemplate template) {
		this.template = template;
		return this.template;
	}

	public List<model.Category> getCategories() {
		System.out.println("Quering Categories from DB");
		String sql = "select * from Category";
		return template.query(sql, new CategoryMapper());
	}

	@Override
	public void addCategory(model.Category category) {
		System.out.println("Adding Category to DB");
		String sql = "insert into Category values ((?),(?),(?),(?),(?))";
		template.execute(sql, new PreparedStatementCallback<Integer>() {

			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(0, category.getName());
				ps.setString(1, category.getCategoryId());
				ps.setInt(2, category.getItemCount());
				ps.setBlob(3, (Blob) category.getItems());
				ps.setBlob(4, (Blob) category);
				return ps.executeUpdate();
			}
		});
	}

	@Override
	public List<Category> display() {
		String sql = "select * from Category";
		return template.query(sql, new dao.interfaces.CategoryDao.CategoryMapper());
	}

	@Override
	public Category display(Category object) {

		return null;
	}

	@Override
	public Category display(String id) {
		return null;
	}

	@Override
	public Category create(Category object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category delete(Category object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category update(Category object) {
		// TODO Auto-generated method stub
		return null;
	}
}