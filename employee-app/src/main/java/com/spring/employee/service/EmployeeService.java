package com.spring.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.employee.dao.EmployeeDao;
import com.spring.employee.model.Employee;



@Service
public class EmployeeService {
	
	@Autowired
	public EmployeeDao dao;
	
	//GET
		public List<Employee> getEmployeeData() {

			List<Employee> stList = dao.getEmployeeData(); 
			
			
			return stList;
		}

	//POST
		public Employee getEmployeeDataBasedOnId(int id) {

//			List<Employee> stList = dao.getEmployeeData();
//			Optional<Employee> employee = stList.stream().filter(crs-> (crs.getId()== id)).findFirst();
//			System.out.println(employee.get());
//			
//			return employee.get();
			
			List<Employee> stList = dao.getEmployeeDataBasedOnId(id);
			
			return stList.get(0);
		}

	//PUT
		public boolean addEmployeeData(Employee ep) {
		
			boolean status = dao.addEmployeeData(ep);
			
			return status;

		}

	//DELETE
		public boolean deleteEmployeeData(int id) {

			
			boolean status= dao.deleteEmployeeData(id);
			
			return status;
		}



}
