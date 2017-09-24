package Rahahleah.RahShopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Rahahleah.RahShopping.model.UserModel;
import Rahahleah.shopingbackend.dto.Cart;
import Rahahleah.shopingbackend.dto.CartLine;
import Rahahleah.shoppingbackend.dao.CartLineDAO;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;
	
	//return the cart of the user who has logged in
	private Cart getCart() {
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	//retirn the entire cartLines
	public List<CartLine> getCartLines() {
		return cartLineDAO.list(this.getCart().getId());
	}
	
}
