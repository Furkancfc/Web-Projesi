package service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.implement.CategoryDaoImpl;
import model.Category;

@Service
public class CategoryServiceImpl implements service.interfaces.CategoryService {
	@Autowired
	public dao.implement.CategoryDaoImpl categoryDao;

	@Override
	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}

	public List<Category> getCategories() {
		return categoryDao.getCategories();
	}

	public void createCategory(Category c) {
		categoryDao.create(c);
	}

	public void deleteCategory(Category c) {
		categoryDao.delete(c);
	}

	public List<Category> display() {
		return categoryDao.display();
	}
}