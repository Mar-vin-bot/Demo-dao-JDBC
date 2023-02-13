package app;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department dpto = new Department(1, "Books");
		
		Seller sel = new Seller(21, "Bob", "gmail", new Date(), 3000.00, dpto);
		
		SellerDao seller  = DaoFactory.createSellerDao();

		
		System.out.println(sel);
		

	}

}
