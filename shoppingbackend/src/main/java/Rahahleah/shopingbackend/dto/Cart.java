package Rahahleah.shopingbackend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cart implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	//--------
	//here we want to create a relation one to one instead of creating normal field, link foreign key
	// if you want to add alias you can use@JoinColumn(name="uid")
	@OneToOne
	private User user;
	

	/*	@Column(name="user_id")
	private int userId;
	*/
	@Column(name="grand_total")
	private double grandTotal;
	@Column(name="cart_lines")
	private int cartLines;
	
	
	
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" +  ", grandTotal=" + grandTotal + ", cartLines=" + cartLines
				+ "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	/*
	 * public int getUserId() {
	
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	 */
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public int getCartLines() {
		return cartLines;
	}
	public void setCartLines(int cartLines) {
		this.cartLines = cartLines;
	}
	
	
}
