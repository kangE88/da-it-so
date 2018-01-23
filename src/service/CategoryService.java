package service;

import java.util.List;

import dao.CategoryDao;
import dao.CategoryDaoImpl;
import dto.Category;

public class CategoryService implements CategoryServiceImpl{
	CategoryDaoImpl categoryDao = new CategoryDao();
	
	public List<Category> getAllCategories(int state){
		return categoryDao.getAllCategories(state);
	}

	@Override
	public Category getCategory(int seq) {
		// TODO Auto-generated method stub
		return categoryDao.getCategory(seq);
	}
}
