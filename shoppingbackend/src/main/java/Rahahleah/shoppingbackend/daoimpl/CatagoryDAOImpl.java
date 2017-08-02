package Rahahleah.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Rahahleah.shopingbackend.dto.Catagory;
import Rahahleah.shoppingbackend.dao.CatagoryDAO;

@Repository("catagoryDAO")
public class CatagoryDAOImpl implements CatagoryDAO {

	private static List<Catagory> catagories= new ArrayList<>(); 
	static {
		Catagory catagory = new Catagory();
		// adding first catagory;
		catagory.setId(1);
		catagory.setName("Television");
		catagory.setDescription("this is Televison Descrpition");
		catagory.setImageURL("CAT_1.png");
		catagories.add(catagory);		
		
		
		//Second catagory
		catagory = new Catagory();
		catagory.setId(2);
		catagory.setName("Mobile");
		catagory.setDescription("this is  Mobile description");
		catagory.setImageURL("CAT_2.png");
		catagories.add(catagory);
		
		
		//Thierd catagory
		catagory = new Catagory();
		catagory.setId(3);
		catagory.setName("laptop");
		catagory.setDescription("this is  laptop description");
		catagory.setImageURL("CAT_3.png");
		catagories.add(catagory);
		
	}
	@Override
	public List<Catagory> list() {
		
		return catagories;
	}
	@Override
	public Catagory get(int id) {
		//enhanced for loop
		for(Catagory catagory:catagories) {
			if(catagory.getId()==id) return catagory;
		}
		return null;
	}

}
