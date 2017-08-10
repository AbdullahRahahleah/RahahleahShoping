package Rahahleah.shoppingbackend.dao;

import java.util.List;

import Rahahleah.shopingbackend.dto.Category;

public interface CategoryDAO {

	
	
	List<Category> list();
	Category get(int id);
	
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
}
