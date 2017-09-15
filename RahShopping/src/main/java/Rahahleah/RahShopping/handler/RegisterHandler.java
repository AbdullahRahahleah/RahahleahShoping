package Rahahleah.RahShopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import Rahahleah.RahShopping.model.RegisterModel;
import Rahahleah.shopingbackend.dto.Address;
import Rahahleah.shopingbackend.dto.Cart;
import Rahahleah.shopingbackend.dto.User;
import Rahahleah.shoppingbackend.dao.UserDAO;

@Component
public class RegisterHandler {

	
	@Autowired
	private UserDAO userDAO;
	
	public RegisterModel init() {
		
		return new RegisterModel();
	}
	public void addUser(RegisterModel registerModel, User user){
		registerModel.setUser(user);
	}
	public void addBilling(RegisterModel registerModel, Address billing){
		registerModel.setBilling(billing);
	}
	public String saveAll(RegisterModel model) {
		String transitionValue="success";
		//fetch the user
		User user=model.getUser();
		if(user.getRole().equals("USER")){
			Cart cart=new Cart();
			cart.setUser(user);
			user.setCart(cart);			
		}
		//save the user
		userDAO.addUser(user);
		
		//get the Addreess
		Address billing =model.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		userDAO.addAddress(billing);
		return transitionValue;
		
	}
	public String validateUser(User user, MessageContext error){
		String transitionValue="success";
		// checking if password matches the confirm passowrd
		if(!(user.getPassword().equals(user.getConfirmPassword()))){
			error.addMessage( new MessageBuilder().
					error().source("confirmPassword").
					defaultText("password does not match the confirm password!").
					build());
			
			transitionValue="failure";
			
		}
		//check the uniquness of the email
		if(userDAO.getByEmail(user.getEmail())!=null){	
			error.addMessage( new MessageBuilder().
					error().source("email").
					defaultText("email is not unique!").
					build());
			transitionValue="failure";
		}
		return transitionValue;
		
	}
	
}
