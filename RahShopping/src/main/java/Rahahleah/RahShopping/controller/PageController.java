package Rahahleah.RahShopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


//here we define controller to tell the spring that this my controller class
@Controller
public class PageController {	
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
		mv.addObject("greeting","Welcome to spring Web MVC from model and view method in controller");
		return mv;
	}
	
	
	//here is another mapping which used RequestParam method
	//RequestParam reads from the URL ?greeting=hello
	@RequestMapping(value="/test")
	public ModelAndView test(@RequestParam("greeting")String greeting){
		ModelAndView mv= new ModelAndView("page");
		//to add parameter to Model (Array), then we can read it from page.jsp page
		mv.addObject("greeting",greeting);
		return mv;
	}
	
}
