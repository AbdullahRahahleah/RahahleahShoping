package Rahahleah.shoppingbackend.test;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Rahahleah.shopingbackend.dto.Address;
import Rahahleah.shopingbackend.dto.Cart;
import Rahahleah.shopingbackend.dto.User;
import Rahahleah.shoppingbackend.dao.UserDAO;

public class UserTestCase {
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private static User user = null;
	private static Cart cart = null;
	private static Address address = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("Rahahleah.shoppingbackend");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	/*
	 * @Test public void testAdd() {
	 * 
	 * //test Add user user=new User(); user.setFirstName("TestUserFirstName");
	 * user.setLastName("TestUserLastName"); user.setEmail("TestUserEmail");
	 * user.setContactNumber("Test45848"); user.setPassword("TestUserPassword");
	 * user.setRole("USER");
	 * assertEquals("failed to add user",true,userDAO.addUser(user));
	 * 
	 * 
	 * //test Add address address=new Address();
	 * address.setAddressLineOne("TestAddresslineone");
	 * address.setAddressLineTwo("TestAddressline2");
	 * address.setCity("TestAddressCity"); address.setState("TestAddressstate");
	 * address.setCountry("TestCountry"); address.setPostalCode("21342");
	 * address.setBilling(true); //link the user with address
	 * address.setUserId(user.getId());
	 * assertEquals("Failed to add addreess",true,userDAO.addAddress(address));
	 * 
	 * if(user.getRole().equals("USER")) { //create a cart for this user
	 * cart=new Cart(); cart.setUser(user);
	 * 
	 * assertEquals("Failed to add Cart!",true,userDAO.addCart(cart));
	 * 
	 * 
	 * // add a shipping address for this user
	 * 
	 * address=new Address(); address.setAddressLineOne("Shipping address");
	 * address.setAddressLineTwo("Near Kudret"); address.setCity("Irbid");
	 * address.setState("Amman"); address.setCountry("Jordan");
	 * address.setPostalCode("400001");
	 * 
	 * //set Shipping to true address.setShipping(true);
	 * 
	 * //link to a user address.setUserId(user.getId());
	 * 
	 * assertEquals("Failed to add shipping address",true,userDAO.addAddress(
	 * address)); }
	 * 
	 * }
	 */
	@Test
	public void testAdd() {

		// test Add user
		user = new User();
		user.setFirstName("TestUserFirstName");
		user.setLastName("TestUserLastName");
		user.setEmail("TestUserEmail");
		user.setContactNumber("Test45848");
		user.setPassword("TestUserPassword");
		user.setRole("USER");
		
		if (user.getRole().equals("USER")) {
			// craete a cart for this user
			cart = new Cart();
			cart.setUser(user);
			//Attach cart with the user
			user.setCart(cart);
		}
		//add the user
		assertEquals("failed to add user", true, userDAO.addUser(user));
	}

}
