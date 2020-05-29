package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {

	private DepartmentDao dao = DaoFactory.createDepartmentDao();

	public List<Department> findAll() {

		// List <Department> list = new ArrayList<>();
		// list.add(new Department (1, "Contabillidade"));
		// list.add(new Department(2, "Informática"));
		// list.add(new Department(3, "Markting"));
		// return list;

		return dao.findAll();
	}
	
	public void saveOrUpdate(Department obj) {
		if (obj.getId()==null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	//remover um departamento do banco de dados 
	public void remove(Department obj) {
		dao.deleteById(obj.getId());
	}

}
