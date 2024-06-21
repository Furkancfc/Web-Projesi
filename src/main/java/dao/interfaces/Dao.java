package dao.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class Dao<T> {
	@Autowired
	public JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	@Autowired
	public JdbcTemplate setTemplate(JdbcTemplate template) {
		this.template = template;
		return this.template;
	}

	public abstract T display(T t);

	public abstract T display(String tId);

	public abstract T create(T t);

	public abstract void delete(T t);

	public abstract void delete(String tId);

	public abstract T update(T t);
}
