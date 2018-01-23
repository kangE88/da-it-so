package dao;

import java.util.List;

import dto.AbilityBbs;
import dto.Category;
import dto.ItemBbs;

public interface CategoryDaoImpl {
	public List<Category> getAllCategories(int state);
	public Category getCategory(int seq) ;
}
