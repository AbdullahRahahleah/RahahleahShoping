package Rahahleah.RahShopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import Rahahleah.shopingbackend.dto.Product;

//To apply custom validator using spring on file Upload 05-06
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}
	//used when we upload a file in the managmentController to check the file
	@Override
	public void validate(Object target, Errors errors) {		
		
		Product product =(Product) target;
		//wether file has been selected or not
		if (product.getFile() ==null || product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null,"please select and Image file to upload! ");
			return;
		}
		
		
		if (! (product.getFile().getContentType().equals("image/jpeg") ||
				product.getFile().getContentType().equals("image/png") ||
				product.getFile().getContentType().equals("image/gif")
				))
			{
				errors.rejectValue("file", null,"Please use only image file for upload!");
				return;
			}
	
	
	}

}
