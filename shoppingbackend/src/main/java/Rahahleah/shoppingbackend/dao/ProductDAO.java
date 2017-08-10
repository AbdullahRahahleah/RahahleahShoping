package Rahahleah.shoppingbackend.dao;

import java.util.List;

import Rahahleah.shopingbackend.dto.Product;

public interface ProductDAO {

	//Main CRUD
	Product get(int productId);
	boolean add(Product product);
	boolean update (Product product);
	boolean delete(Product product);
	
	List<Product> list();
	
	//business methods
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
	
	
}
