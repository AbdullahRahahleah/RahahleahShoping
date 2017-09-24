package Rahahleah.shoppingbackend.dao;

import java.util.List;

import Rahahleah.shopingbackend.dto.Cart;
import Rahahleah.shopingbackend.dto.CartLine;

public interface CartLineDAO {
	
	public CartLine get(int id);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean delete(CartLine cartLine);
	public List<CartLine> list(int cartId);
	
	
	public List<CartLine> listAvailable(int cartId);
	public CartLine getByCartAndProduct(int cartId,int productId);
	
	//as the cart already added by adduser method with cascade property
	boolean updateCart(Cart cart);

}
