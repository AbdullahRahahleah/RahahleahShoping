package Rahahleah.RahShopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Rahahleah.RahShopping.model.UserModel;
import Rahahleah.shopingbackend.dto.Cart;
import Rahahleah.shopingbackend.dto.CartLine;
import Rahahleah.shopingbackend.dto.Product;
import Rahahleah.shoppingbackend.dao.CartLineDAO;
import Rahahleah.shoppingbackend.dao.ProductDAO;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ProductDAO productDAO;
	//return the cart of the user who has logged in
	private Cart getCart() {
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	//retirn the entire cartLines
	public List<CartLine> getCartLines() {
		return cartLineDAO.list(this.getCart().getId());
	}
	public String updateCartLine(int cartLineId, int count) {
		//fetch the cart Line
		CartLine cartLine =cartLineDAO.get(cartLineId);
		if(cartLine==null){
			return "result=error";
		}
		else {
			Product product = cartLine.getProduct();
			double oldTotal=cartLine.getTotal();
			if(product.getQuantity()<=count){
				count=product.getQuantity();
			}
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			
			cartLine.setTotal(product.getUnitPrice()*count);
			cartLineDAO.update(cartLine);
			Cart cart=this.getCart();
			cart.setGrandTotal(cart.getGrandTotal()-oldTotal+cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
		}
		
	}
	public String deleteCartLine(int cartLineId) {
		//fetch the cartLine
		CartLine cartLine =cartLineDAO.get(cartLineId);	
		if(cartLine==null){
			return "result=error";
		}
		else {
			//update the cart
			Cart cart=this.getCart();
			cart.setGrandTotal(cart.getGrandTotal()-cartLine.getTotal());
			cart.setCartLines(cart.getCartLines()-1);
			cartLineDAO.updateCart(cart);
			//remove the cart Line
			cartLineDAO.delete(cartLine);
			return "result=deleted";
		}
	}
	public String addCartLine(int productId) {
		String response=null;
		Cart cart= this.getCart();
		CartLine cartLine=cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if(cartLine==null){
			//add the new cartLine
			cartLine =new CartLine();
			//fetch  the product
			Product product=productDAO.get(productId);
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			//because this is the first time
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			cartLineDAO.add(cartLine);
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			response="result=added";
		}
		
		
		
		return  response;

	}
	
}
