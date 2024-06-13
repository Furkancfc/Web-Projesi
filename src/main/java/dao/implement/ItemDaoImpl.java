package dao.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import dao.interfaces.ItemDao;
import model.Item;

@Repository
public class ItemDaoImpl implements ItemDao {
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

	@Autowired
	public void setDataSource(DriverManagerDataSource ds) {
		template.setDataSource(ds);
	}

	@Override
	public Item display(Item object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item create(Item object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item delete(Item object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item update(Item object) {
		// TODO Auto-generated method stub
		return null;
	}

}