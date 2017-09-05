package Rahahleah.RahShopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Rahahleah.RahShopping.util.FileUploadUtility;
import Rahahleah.RahShopping.validator.ProductValidator;
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
		
		//This is just to generate a constructor to use it in the manageproduct.jsp form to fill it, then you can use it by post method
		Product nProduct=new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		//here we define our class (product) in manageproduct.jsp using modelAttribute="product" 
		mv.addObject("product", nProduct);
		
		//in this case I came from Post method just to print this message 
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message", "Product submitted successfully !");
				
			}
		}
		
		return mv;
	}
	
	
	//handling product submission from manageProducts.jsp (action ="${contextRoot}/manage/products") and values filled in the mproduct object according to path parameters
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results, Model model,
			HttpServletRequest request ){
		
		
		
		if(mProduct.getId()==0) {
			//just to deal with the new product
			//to Add spring validator on the uploaded file using (productValidator.java)
			new ProductValidator().validate(mProduct, results);
		}
		else {
			//if the existing product doesn't has an image then we need to validate
			if(!mProduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mProduct, results);
			}
		}
				
		
		//check if there are any errors from validation which we put in proudct.java class @NotBlank..etc
 		if(results.hasErrors()){
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission");
			return "page";
		}
		
		logger.info(mProduct.toString());
		
		//to update an existing product (update)
		if(mProduct.getId()==0){
			//create a new product record
			productDAO.add(mProduct);
		}
		else {
			//update existing product
			productDAO.update(mProduct);
		}
		
		
		
		//Just to check we have attached a file then attach the file using our method 
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
			
		}
		
		
		
		//redirect to Get method (Above) to display the message (product submitted correctly )
		return "redirect:/manage/products?operation=product";
	}
	
	//called from myapp.js at ($.post(activationUrl,function(data){)
	@RequestMapping(value="/product/{id}/activation",method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id)  {
		//to fetch the product from DB
		Product product=productDAO.get(id);
		boolean isActive=product.isActive();
		//to Activate and deactivate the product 
		product.setActive(!isActive);
		
		productDAO.update(product);
		
		return (isActive)
				?"You have succesfully deactivated the product with Id"+ product.getId()
				:"You have succesfully activated the product with Id"  + product.getId();
	}
	
	//categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
	// here to show the product in edit mode in manage product
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id) {
		//operation read from Post method	
		ModelAndView mv=new ModelAndView("page");		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		//fetch the product from database
		Product nProduct=productDAO.get(id);
		mv.addObject("product", nProduct);
		
		return mv;
	}
}
