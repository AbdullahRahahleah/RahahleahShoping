package Rahahleah.RahShopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Rahahleah.RahShopping.exception.ProductNotFoundException;
import Rahahleah.shopingbackend.dto.Category;
import Rahahleah.shopingbackend.dto.Product;
import Rahahleah.shoppingbackend.dao.CategoryDAO;
import Rahahleah.shoppingbackend.dao.ProductDAO;


//THis class named handler


//here we define controller to tell the spring that this my controller class
@Controller
public class PageController {
	
	//used to add logs 
	private static final Logger logger =LoggerFactory.getLogger(PageController.class);
	
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	
	//here we used same method to handle various url mapping
	// in RequestMapping we define multiple URL to handle
	//View means JSP page
	//Model = Values (key,value)
	//View = JSP pages
	@RequestMapping(value={"/","/home","/index"})
	public ModelAndView index(){
		//page used in the dispatcher-servlet.xml file to get view (jsp file)page.jsps
		ModelAndView mv= new ModelAndView("page");
		//to add parameter to Model (Array), then we can read it from page.jsp page
//		mv.addObject("greeting","Welcome to spring Web MVC from model and view method in controller");
		mv.addObject("title","Home");
		
		//test to Add logs
		logger.info("Inside page controller Index method-Info");
		logger.debug("Inside page controller Index method-Debug");
		
		//passing the list of categories
		mv.addObject("categories",categoryDAO.list());
		
		
		mv.addObject("userClickHome",true);
		return mv;
	}
	
	@RequestMapping(value={"/about"})
	public ModelAndView about(){
		//page used in the dispatcher-servlet.xml file to get view (jsp file)page.jsps
		ModelAndView mv= new ModelAndView("page");
		//to add parameter to Model (Array), then we can read it from page.jsp page
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout",true);
		return mv;
	}
	
	@RequestMapping(value={"/contact"})
	public ModelAndView contact(){
		//page used in the dispatcher-servlet.xml file to get view (jsp file)page.jsps
		ModelAndView mv= new ModelAndView("page");
		//to add parameter to Model (Array), then we can read it from page.jsp page
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContact",true);
		
		return mv;
	}
	
	/*
	 * Methods to load all the products and based on category
	 */
	@RequestMapping(value={"/show/all/products"})
	public ModelAndView showAllProducts(){
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("title","All Products");
		
		//passing the list of categories from DB
		mv.addObject("categories",categoryDAO.list());
	
		mv.addObject("userClickAllProducts",true);
		return mv;
	}
	
	@RequestMapping(value={"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id") int id){
		ModelAndView mv= new ModelAndView("page");
		
		//catagaroyDAO to fetch a signle category
		Category category=null;
		category=categoryDAO.get(id);
		
		mv.addObject("title",category.getName());
		
		//passing the list of categories, in this case it's reading the values from the DB's
		mv.addObject("categories",categoryDAO.list());
	
		//passing the single category object
		mv.addObject("category", category);
		
		mv.addObject("userClickCategoryProducts",true);
		return mv;
	}
	
	/*
	 * Viewing Single Product
	 */
	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable("id") int id) throws ProductNotFoundException{
		ModelAndView mv= new ModelAndView("page");
		Product product =productDAO.get(id);
		if(product==null)
			throw new ProductNotFoundException();
		product.setViews(product.getViews()+1);	
		productDAO.update(product);
		
		mv.addObject("title",product.getName());
		mv.addObject("product",product);		
		mv.addObject("userClickShowProduct", true);
			
		return mv;		
	}
	
	/*
	 * Those are examples to use path variable and Requestparam video 02-04 
	 * 
	//here is another mapping which used RequestParam method
	//RequestParam reads from the URL ?greeting=hello like 
	//http://localhost:8080/RahShopping/test?greeting=hello%20from%20url
	@RequestMapping(value="/test")
	public ModelAndView test(@RequestParam(value ="greeting",required =false)String greeting){
		if(greeting==null){
			greeting="Hello there";
		}
		ModelAndView mv= new ModelAndView("page");
		//to add parameter to Model (Array), then we can read it from page.jsp page
		mv.addObject("greeting",greeting);
		return mv;
	}
	
	
	//Here we are using path variable 
	//http://localhost:8080/RahShopping/test/hello%20from%20path%20variable
	@RequestMapping(value="/test/{greeting}")
	public ModelAndView test(@PathVariable(value ="greeting",required =false)String greeting){
		if(greeting==null){
			greeting="Hello there";
		}
		ModelAndView mv= new ModelAndView("page");
		//to add parameter to Model (Array), then we can read it from page.jsp page
		mv.addObject("greeting",greeting);
		return mv;
	}
	*/
	/*
	 * Webflow + requestMapping
	 * 
	// having simillar mapping to our flow id 
	//We have added a proprty to dispatcher-servlet.xml which force the system to deal with the flow configuration not with requestmapping 
	//proprty is <property name="order" value="-1"></property>
	@RequestMapping(value="/register")
	public ModelAndView showSingleProduct() {
		ModelAndView mv= new ModelAndView("page");
		//to add parameter to Model (Array), then we can read it from page.jsp page
		mv.addObject("title","Contact Us");	
		return mv;
		
		
	}
	*/
	//login spring secuirty, we define the URL in the spring-security.xml <form-login
	//logout readirected from the method (logout) bellow 
	@RequestMapping(value="/login")
	public ModelAndView showSingleProduct(@RequestParam(name ="error",required=false)String error,
										@RequestParam(name ="logout",required=false)String logout) {
		ModelAndView mv= new ModelAndView("login");
		//to add parameter to Model (Array), then we can read it from page.jsp page
		if(error!=null){
			mv.addObject("message", "Invalid User name and-or password!");
		}
		if(logout!=null){
			mv.addObject("logout", "User has successfully logged out !");
		}
		
		
		mv.addObject("title","Login");	
		return mv;
	}
	
	//Access denied page
	@RequestMapping(value="/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv= new ModelAndView("error");
		mv.addObject("title","403- Access Denied ");
		mv.addObject("errorTitle","Aha! Caught you");	
		mv.addObject("errorDescription","You are not authorized to view this page!");	

		
		return mv;
	}
	// Logout
	@RequestMapping(value="/perform-logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		//first we are going to fetch the authentication
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth!=null){
			new SecurityContextLogoutHandler().logout(request, response, auth);						
		}
		
		return "redirect:/login?logout";
		
	}	
	
}
