package app;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("=== 1 Teste FindById Seller ");
		Seller sell = sellerDao.findById(3);
		System.out.println(sell);

		System.out.println("=== 2 Teste FindByIdDepartament dpto ");
		Department departament = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartament(departament);

		for (Seller obj : list) {
			System.out.println(obj);
		}

		System.out.println("=== 3 Teste FindIdAll ");
		list = sellerDao.findAll();

		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("=== 4 Teste INSERT ");
		Seller newSeller = new Seller(null, "Black Alien ", "babilon@gmal.com", new Date(), 4000.0, departament);
		sellerDao.insert(newSeller);
		System.out.println("OK " + newSeller.getId());

	}

}
