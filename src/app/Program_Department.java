package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program_Department {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		DepartmentDao dptoDao = DaoFactory.createDepartamentDao();

		System.out.println("=== 1 Teste FindById dpto ");
		Department dpto = dptoDao.findById(3);
		System.out.println(dpto);

		List<Department> list = new ArrayList<>();
		System.out.println("=== 3 findAll ");
		list = dptoDao.findAll();

//		for (Department obj : list) {
//			System.out.println(obj);
//		}
		
		list.forEach(System.out::println);

		System.out.println("=== 4 Teste INSERT ");
		Department newDpto = new Department(null, "Financeiro ");
		dptoDao.insert(newDpto);
		System.out.println("id new dpto " + newDpto.getId());

		System.out.println("=== 5 Teste update ");
		dpto = dptoDao.findById(3);
		dpto.setName("Xadrez verbal ");
		dptoDao.update(dpto);
		System.out.println("Update depto");

		System.out.println("=== 6 Teste delete ");
		System.out.println("Enter with numeber of departament ");
		int id = sc.nextInt();
		dptoDao.deleteById(id);
		System.out.println("ok");

		sc.close();
	}

}
