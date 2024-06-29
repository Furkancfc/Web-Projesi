package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.*;

import dao.interfaces.CategoryDao;
import model.Category;

@Repository
public class CategoryDaoImpl extends CategoryDao {

	public List<Category> getCategories() {
		String sql = "select * from Category";
		return template.query(sql, new CategoryMapper());
	}

	public Category getCategory(String cName) {
		String sql = "select * from name=(?)";
		return template.execute(sql, new PreparedStatementCallback<Category>() {
			@Override
			public Category doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, cName);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new CategoryMapper().mapRow(rs, rs.getRow());
				} else
					return null;
			}
		});
	}

	public Category addCategory(model.Category object) {
		return create(object);
	}

	public void deleteCategory(model.Category object) {
		delete(object);
	}

	public void deleteCategory(String objectId) {
		delete(objectId);
	}

	public Category updateCategory(model.Category object) {
		return update(object);
	}
}