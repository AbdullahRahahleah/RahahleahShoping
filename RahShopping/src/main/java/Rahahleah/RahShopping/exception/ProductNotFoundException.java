package Rahahleah.RahShopping.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable{
	
	
	
	private String message; 
	private static final long serialVersionUID=1L;
	public String getMessage() {
		return message;
	}


	public ProductNotFoundException() {
		this("Product is not avialable");		
	}

	public ProductNotFoundException(String string) {
		this.message=System.currentTimeMillis()+": " + message;
	}

}
