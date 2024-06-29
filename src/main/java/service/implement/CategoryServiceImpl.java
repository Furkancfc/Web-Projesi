package service.implement;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.implement.CategoryDaoImpl;
import model.Category;
import model.Item;

@Service
public class CategoryServiceImpl extends service.interfaces.CategoryService {
	@Autowired
	public dao.implement.CategoryDaoImpl categoryDao;

	public CategoryServiceImpl() {
		super();
	}

	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}

	public List<Category> getCategories() {
		return categoryDao.getCategories();
	}

	public void updateCategory(Category c) {
		categoryDao.updateCategory(c);
	}

	public void deleteCategory(Category c) {
		categoryDao.deleteCategory(c);
	}

	public Map<String, Item> getCategoryItems(Category c) {
		return categoryDao.getCategory(c.getName()).getItems();
	}
}