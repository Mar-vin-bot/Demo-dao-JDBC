package app;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

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
		
		list.forEach(System.out::println);

//		for (Seller obj : list) {
//			System.out.println(obj);
//		}
		
		System.out.println("=== 4 Teste INSERT ");
		Seller newSeller = new Seller(null, "Black Alien ", "babilon@gmal.com", new Date(), 4000.0, departament);
		sellerDao.insert(newSeller);
		System.out.println("OK " + newSeller.getId());
		
		
		System.out.println("=== 5 Teste UPDATE ");
		sell = sellerDao.findById(1);
		sell.setName("Cegos do Castelo ");
		sellerDao.update(sell);
		System.out.println("Update");
		
		System.out.println("=== 6 Teste deleteById ");
		System.out.println("Entre with number be delete");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete ");

		sc.close();
	}

}
