package dao.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import model.Account;

@Repository
public class UserDaoImpl implements dao.interfaces.UserDao {
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

	public model.Account getUser(String email) {
//		String sql = "select * from Account
		return null;
	}

	public model.Account createUser() {
		return null;
	}
//	public 

	@Override
	public Account display(Account object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account create(Account object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account delete(Account object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account update(Account object) {
		// TODO Auto-generated method stub
		return null;
	}
}