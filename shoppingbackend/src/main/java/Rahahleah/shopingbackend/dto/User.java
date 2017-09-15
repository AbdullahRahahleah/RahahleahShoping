package Rahahleah.shopingbackend.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="user_detail")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	@NotBlank(message="please Enter First Name!")
	private String firstName;
	
	@Column(name="last_name")
	@NotBlank(message="please Enter Last Name!")
	private String lastName;
	
	@NotBlank(message="please Enter Email address!")
	private String email;
	
	@Column(name="contact_number")
	@NotBlank(message="please Enter Contact Number!")
	private String contactNumber;
	
	private String role;
	@NotBlank(message="please Enter Password!")
	private String password;
	
	//confirm password transient field
	@Transient
	private String confirmPassword;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	private boolean enabled=true;
	
	
	//one to one bidirectional
	//this means every user has his own cart else he has a one with null;
	//with mappedBy := we used the same name for the object in the Cart.java class, 
	//this mean the User is part and Cart is child, but if we didn't use the mappedBy property we will get a foreign keys for cartId in the user table
	//cascade : if you have create a parent (user) and you have attached a child (cart) then the system automatically adds the child to DB, and if
	// 			removed the parent, it's child will be removed also.
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
	private Cart cart;
	
	
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", role=" + role + ", password=" + password + ", enabled="
				+ enabled + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	

}
