package Rahahleah.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Rahahleah.shopingbackend.dto.Category;
import Rahahleah.shoppingbackend.dao.CategoryDAO;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("Rahahleah.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
				
		
	}
/*
	@Test
	public void testAddCategory(){
		
		category = new Category();
		
		category.setName("Television");
		category.setDescription("this is Televison Descrpition");
		category.setImageURL("CAT_1.png");
		System.out.println("Hello "+category);

		//here we send three paramerters (message, expected result from a method, method it self)
		assertEquals("sucessfully added a category inside the table(from test class)",true,categoryDAO.add(category));
		System.out.println(category);
		
	}
	*/
	/*
	@Test
	public void testGetCategory() { 
		category =categoryDAO.get(1);
		assertEquals("sucessfully fetched a single category from the table(from test class)","TV",category.getName());

	}
	*/
	/*
	@Test
	public void testUpdateCategory() { 
		category =categoryDAO.get(1);
		category.setName("TV");
		assertEquals("sucessfully update a single category from the table(from test class)",true,categoryDAO.update(category));
	}
	*/
	
	/*
	@Test
	public void testDeleteCategory() { 
		category =categoryDAO.get(1);
		category.setActive(false);
		assertEquals("sucessfully deleted a single category from the table(from test class)",true,categoryDAO.delete(category));
	}
	*/
	/*
	@Test
	public void testGetListCategory() { 
	
		assertEquals("sucessfully fetched the list catagories from the table(from test class)",3,categoryDAO.list().size());
	}
	*/
	@Test
	public void testCRUDCategory(){
		
	}
	
	
	
}
