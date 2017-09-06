package Rahahleah.shoppingbackend.dao;

import Rahahleah.shopingbackend.dto.Address;
import Rahahleah.shopingbackend.dto.Cart;
import Rahahleah.shopingbackend.dto.User;

public interface UserDAO {
	boolean addUser(User user);
	User getByEmail(String email);
	
	
	boolean addAddress(Address address);
	//as the cart already added by adduser method with cascade property
	boolean updateCart(Cart cart);

}
