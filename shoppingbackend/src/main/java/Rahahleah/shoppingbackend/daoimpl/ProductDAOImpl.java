package Rahahleah.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Rahahleah.shopingbackend.dto.Product;
import Rahahleah.shoppingbackend.dao.ProductDAO;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * get single product
	 */
	@Override
	public Product get(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		}

	}

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(Product product) {
		try {
			//soft delete
			product.setActive(false);
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> listActiveProducts() {
		try {
			//product is entity class name (in DTO package)
			String pstm = "FROM Product WHERE active =:active";
			Query query = sessionFactory.getCurrentSession().createQuery(pstm,Product.class);
			query.setParameter("active", true);
			return query.getResultList();	
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		try {
			String pstm = "FROM Product WHERE active =:active AND categoryId = :categoryId";
			Query query = sessionFactory.getCurrentSession().createQuery(pstm);
			query.setParameter("active", true);
			query.setParameter("categoryId", Integer.valueOf(categoryId));
			return query.getResultList();	
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		try {
			String pstm = "FROM Product WHERE active =:active ORDER BY id DESC";
			Query query = sessionFactory.getCurrentSession().createQuery(pstm);
			query.setParameter("active", true);
			//There is anothere way to get a sub list
			//query.setFirstResult(0).setMaxResults(count)
			return query.getResultList().subList(0, count);
		}
		catch (Exception ex) {
				ex.printStackTrace();
				return null;
		}
	}

}
