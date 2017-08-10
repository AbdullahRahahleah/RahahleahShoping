package Rahahleah.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Rahahleah.shopingbackend.dto.Category;
import Rahahleah.shoppingbackend.dao.CategoryDAO;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> list() {
		
		// here we write HQL query (Category=entiry name or class name in DTO)
		String selectActiveCategory="FROM Category WHERE active = :active";
		Query query=sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		//here we set the value of parammeter active (:active) to true and (active is a variable)
		query.setParameter("active", true);
		
		return query.getResultList();
	}

	// used by page.jsp to deploy catagories
	/*
	 * Getting single category based on id
	 * 
	 */
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	// used to add using hibernate
	@Override
	public boolean add(Category category) {
		try {
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	/*
	 * update a signle category
	 * 
	 */
	@Override
	public boolean update(Category category) {

		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		try {
			category.setActive(false);
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	/*
	 * 
	 * samples
	 * 
	 * 
	 * private static List<Category> catagories= new ArrayList<>(); static {
	 * Category category = new Category(); // adding first category;
	 * category.setId(1); category.setName("Television");
	 * category.setDescription("this is Televison Descrpition");
	 * category.setImageURL("CAT_1.png"); catagories.add(category);
	 * 
	 * 
	 * //Second category category = new Category(); category.setId(2);
	 * category.setName("Mobile");
	 * category.setDescription("this is  Mobile description");
	 * category.setImageURL("CAT_2.png"); catagories.add(category);
	 * 
	 * 
	 * //Thierd category category = new Category(); category.setId(3);
	 * category.setName("laptop");
	 * category.setDescription("this is  laptop description");
	 * category.setImageURL("CAT_3.png"); catagories.add(category);
	 * 
	 * }
	 * 
	 * @Override public Category get(int id) { //enhanced for loop for(Category
	 * category:catagories) { if(category.getId()==id) return category; } return
	 * null; }
	 */

}
