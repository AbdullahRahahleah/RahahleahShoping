package Rahahleah.shoppingbackend.dao;

import java.util.List;

import Rahahleah.shopingbackend.dto.Address;
import Rahahleah.shopingbackend.dto.Cart;
import Rahahleah.shopingbackend.dto.User;

public interface UserDAO {
	boolean addUser(User user);
	User getByEmail(String email);
	
	
	boolean addAddress(Address address);
	//alternative for better performance
	//Address getBillingAddress(User user);
	//List<Address> listShippingAdresses(User user);
	
	
	Address getBillingAddress(int userId);
	List<Address> listShippingAdresses(int userId);
	
	//as the cart already added by adduser method with cascade property
	boolean updateCart(Cart cart);

}
