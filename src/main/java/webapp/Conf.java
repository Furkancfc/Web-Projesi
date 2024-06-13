package webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.WebApplicationContext;

import java.sql.DriverManager;
import com.mysql.cj.jdbc.*;
import com.mysql.jdbc.*;

import dao.implement.CartDaoImpl;
import dao.implement.CategoryDaoImpl;
import dao.implement.ItemDaoImpl;
import dao.implement.UserDaoImpl;

@Configuration
public class Conf {
	@Autowired
	private static DriverManagerDataSource ds;
	@Autowired
	private static JdbcTemplate template;
	@Autowired
	private static WebApplicationContext context;
	
	public static JdbcTemplate getTemplate() {
		return template;
	}

	public static DriverManagerDataSource setDataSource() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ds = new DriverManagerDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/webproject2");
		ds.setPassword("projectServer");
		ds.setUsername("projectServer");
		return ds;
	}
}