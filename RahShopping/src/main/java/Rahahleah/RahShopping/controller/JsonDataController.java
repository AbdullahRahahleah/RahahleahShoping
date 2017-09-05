package Rahahleah.RahShopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Rahahleah.shopingbackend.dto.Product;
import Rahahleah.shoppingbackend.dao.ProductDAO;

/*
 * important points :-
 * 1- We want methods to return values in form of JSON
 * 2- All the methods here needs to be mapped to /json/data
 * 3- You need to install Postman extension to chrome
 * 4- @ResponseBody is the responsible to convert java objects to JSON 
 * 5- You can find data tables JQuery from https://www.datatables.net/
 */

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProducts() {

		return productDAO.listActiveProducts();

	}
	
	@RequestMapping("/admin/all/products")
	@ResponseBody
	public List<Product> getAllProductsForAdmin() {

		return productDAO.list();

	}
	

	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getAllProductsByCategory(@PathVariable int id) {

		return productDAO.listActiveProductsByCategory(id);
	}
	
	
	
	
}
