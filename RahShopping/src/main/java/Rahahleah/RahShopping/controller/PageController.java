package Rahahleah.RahShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Rahahleah.shopingbackend.dto.Catagory;
import Rahahleah.shoppingbackend.dao.CatagoryDAO;


//THis class named handler


//here we define controller to tell the spring that this my controller class
@Controller
public class PageController {
	
	@Autowired
	private CatagoryDAO catagoryDAO;
	
	
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
		
		//passing the list of catagories
		mv.addObject("catagories",catagoryDAO.list());
		
		
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
	 * Methods to load all the products and based on catagory
	 */
	@RequestMapping(value={"/show/all/products"})
	public ModelAndView showAllProducts(){
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("title","All Products");
		
		//passing the list of catagories
		mv.addObject("catagories",catagoryDAO.list());
	
		mv.addObject("userClickAllProducts",true);
		return mv;
	}
	
	@RequestMapping(value={"/show/catagory/{id}/products"})
	public ModelAndView showCatagoryProducts(@PathVariable("id") int id){
		ModelAndView mv= new ModelAndView("page");
		
		//catagaroyDAO to fetch a signle catagory
		Catagory catagory=null;
		catagory=catagoryDAO.get(id);
		
		mv.addObject("title",catagory.getName());
		
		//passing the list of catagories
		mv.addObject("catagories",catagoryDAO.list());
	
		//passing the single catagory object
		mv.addObject("catagory", catagory);
		
		mv.addObject("userClickCatagoryProducts",true);
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
	
	
}
