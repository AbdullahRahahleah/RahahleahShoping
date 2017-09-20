package Rahahleah.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Rahahleah.shopingbackend.dto.CartLine;
import Rahahleah.shoppingbackend.dao.CartLineDAO;


@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	
	@Override
	public CartLine get(int id) {
		return sessionFactory.getCurrentSession().get(CartLine.class,id);		
	}
	
	@Override
	public boolean add(CartLine cartLine) {
		try{
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartLine) {
		try{
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}	
		}

	@Override
	public boolean delete(CartLine cartline) {
		try {
			cartline.setAvailable(false);
			sessionFactory.getCurrentSession().update(cartline);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<CartLine> list(int cartId) {
		String query="FROM CartLine WHERE cartId=:cartId";
		return sessionFactory.getCurrentSession()
				.createQuery(query,CartLine.class)
				.setParameter("cartId", cartId)
				.getResultList();
	
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
