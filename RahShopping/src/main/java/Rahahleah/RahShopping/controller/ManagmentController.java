package Rahahleah.RahShopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Rahahleah.shopingbackend.dto.Category;
import Rahahleah.shopingbackend.dto.Product;
import Rahahleah.shoppingbackend.dao.CategoryDAO;


@Controller
@RequestMapping("/manage")
public class ManagmentController {

	@Autowired
	private CategoryDAO categoryDAO;

	
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProducts() {
		ModelAndView mv=new ModelAndView("page");
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		//to add product
		Product nProduct=new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product", nProduct);
		
		
		return mv;
	}
	
	//categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
}
