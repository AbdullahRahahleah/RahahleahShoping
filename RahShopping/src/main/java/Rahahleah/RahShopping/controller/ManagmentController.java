package Rahahleah.RahShopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Rahahleah.shopingbackend.dto.Category;
import Rahahleah.shopingbackend.dto.Product;
import Rahahleah.shoppingbackend.dao.CategoryDAO;
import Rahahleah.shoppingbackend.dao.ProductDAO;


@Controller
@RequestMapping("/manage")
public class ManagmentController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;
	
	//to add logger
	private static final Logger logger = LoggerFactory.getLogger(ManagmentController.class);
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false)String operation) {
		//operation read from Post method	
		ModelAndView mv=new ModelAndView("page");
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		//to add product
		Product nProduct=new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product", nProduct);
		
		//in this case I came from Post method
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message", "Product submitted successfully !");
				
			}
		}
		
		return mv;
	}
	
	
	//handling product submission from manageProducts.jsp (action ="${contextRoot}/manage/products") and values filled in the mproduct object according to path parameters
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@ModelAttribute("product") Product mproduct){
		
		logger.info(mproduct.toString());
		
		//create a new product record
		productDAO.add(mproduct);
		//redirect to Get method (Above)
		return "redirect:/manage/products?operation=product";
	}
	
	
	//categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
}
