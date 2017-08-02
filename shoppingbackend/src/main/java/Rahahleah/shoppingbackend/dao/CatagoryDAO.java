package Rahahleah.shoppingbackend.dao;

import java.util.List;

import Rahahleah.shopingbackend.dto.Catagory;

public interface CatagoryDAO {

	List<Catagory> list();
	Catagory get(int id);
	
}
