package model.services;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {

	private DepartmentDao dao = DaoFactory.createDepartmentDao();

	public List<Department> findAll() {

		// List <Department> list = new ArrayList<>();
		// list.add(new Department (1, "Contabillidade"));
		// list.add(new Department(2, "Inform�tica"));
		// list.add(new Department(3, "Markting"));
		// return list;

		return dao.findAll();

	}

}
