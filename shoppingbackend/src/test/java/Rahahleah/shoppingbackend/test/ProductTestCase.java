package Rahahleah.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Rahahleah.shopingbackend.dto.Product;
import Rahahleah.shoppingbackend.dao.ProductDAO;

public class ProductTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();	
		context.scan("Rahahleah.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
	
	
	/*
	@Test
	public void testGetLatestActiveProducts() {
		assertEquals("sucessfully fetched a list of products from the table",3,productDAO.getLatestActiveProducts(3).size());
		List<Product> temp=productDAO.getLatestActiveProducts(3);
		for(int i=0;i<temp.size();i++) {
			System.out.println(temp.get(i));
		}
	}
	*/
	
	/*
	@Test
	public void testGetListActiveProductsByCategory() {
		assertEquals("sucessfully fetched a list of products from the table",2,productDAO.listActiveProductsByCategory(1).size());
			List<Product> temp=productDAO.listActiveProductsByCategory(1);
		for(int i=0;i<temp.size();i++) {
			System.out.println(temp.get(i));
		}
	}
	*/
	/*
	@Test
	public void testGetListActiveProducts() {
		assertEquals("sucessfully fetched a list of products from the table",6,productDAO.listActiveProducts().size());
				List<Product> temp=productDAO.listActiveProducts();
		for(int i=0;i<temp.size();i++) {
			System.out.println(temp.get(i));
		}
	}
	*/	
	
	/*
	@Test
	public void testGetListgetListProducts(){
		
		assertEquals("sucessfully fetched a list of products from the table",7,productDAO.list().size());		
	}
	*/
	
	
	
	/*
	@Test
	public void testDeleteProduct () {
		product =productDAO.get(7);
		assertEquals("sucessfully deletes a product inside the table",true,productDAO.delete(product));		
	}
	*/
	
	/*
	@Test
	public void testupdateProduct () {
		product =productDAO.get(6);
		product.setBrand("Addidas");
		product.setActive(false);
		assertEquals("sucessfully updates a product inside the table",true,productDAO.update(product));		
	}
	*/
	
	/*
	@Test
	public void testAddProduct () {
		product = new Product();
		product.setActive(true);
		product.setBrand("Nike");
		product.setName("Boot");
		product.setDescription("This is a test product");
		product.setUnitPrice(124.2);
		product.setQuantity(3);
		product.setCategoryId(3);
		product.setSupplierId(2);
		product.setPurchases(0);
		product.setView(0);
		
		assertEquals("sucessfully added a product inside the table",true,productDAO.add(product));
	}
	*/
	/*
	@Test
	public void testGetProduct() {
		
		product = productDAO.get(2);
		assertEquals("successfully fetched a product from the table","Samsung s7"z,product.getName());
		
	}*/
	
	
	
}
