package Rahahleah.RahShopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;



@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	//to deal with 404 exception
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "The page is not contructed !");
		mv.addObject("errorDescription", "The page you are looking for is not available !");
		mv.addObject("title", "404 Error page");
		return mv;
		
	}
	
	// like in product URL insert wrong intger product Id
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product Not available  !");
		mv.addObject("errorDescription", "The product you are looking for is not available right now !");
		mv.addObject("title", "product Unavailable");
		return mv;
		
	}
	// like in product URL insert non intger for product Id
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Contact Your Administrator!");
		
		//Only for debuging mode application 
		/*StringWriter sw= new StringWriter();
		PrintWriter pw= new PrintWriter(sw);
		ex.printStackTrace(pw);
		mv.addObject("errorDescription", sw.toString());
		*/
		
		mv.addObject("errorDescription", ex.toString());
		mv.addObject("title", "Error");
		return mv;
		
	}
}
