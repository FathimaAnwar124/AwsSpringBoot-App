package com.spring.employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.spring.employee.model.Employee;



@Component
public class EmployeeDao {
	
	@Autowired
	public JdbcTemplate template;
	
	//GET Request call
	public List<Employee> getEmployeeData() {
		
		String sql = "select * from employees ";
		
		List<Employee> employeeList = template.query(sql, new ResultSetExtractor<List<Employee>>() {
			
			@Override
			public List<Employee> extractData(ResultSet rs) throws SQLException , DataAccessException {
				List<Employee> list = new ArrayList<>();
				while(rs.next()) {
					Employee employee = new Employee();
					employee.setId(rs.getInt("id"));
					employee.setName(rs.getString("name"));
					employee.setMobile_no(rs.getInt("mobile_no"));
					employee.setAddress(rs.getString("address"));
					employee.setProject(rs.getString("project"));
					employee.setManager(rs.getString("manager"));
					list.add(employee);
				
				}
				return list;
			}
		});
		
		employeeList.stream().forEach(employee->{
			System.out.println(employee.getId()+"-"+employee.getName()+ "-" + employee.getAddress()+ "-" + employee.getMobile_no()+ "-" + employee.getProject()+ "-" + employee.getManager()) ;
			
		});
		
		return employeeList;

	}

	
	//POST Request Call
	public List<Employee> getEmployeeDataBasedOnId(int id) {
	
		
		String sql = "select * from employees where id = "+id;
		
		List<Employee> employeeList = template.query(sql, new ResultSetExtractor<List<Employee>>() {
		
			@Override
			public List<Employee> extractData(ResultSet rs) throws SQLException , DataAccessException {
				List<Employee> list = new ArrayList<>();
				while(rs.next()) {
					Employee employee = new Employee();
					employee.setId(rs.getInt("id"));
					employee.setName(rs.getString("name"));
					employee.setMobile_no(rs.getInt("mobile_no"));
					employee.setAddress(rs.getString("address"));
					employee.setProject(rs.getString("project"));
					employee.setManager(rs.getString("manager"));
					list.add(employee);
				
				}
				return list;
			}
			
		});
		
		employeeList.stream().forEach(employee->{
			System.out.println(employee.getId()+"-"+employee.getName()+ "-" + employee.getMobile_no()+ "-" + employee.getAddress()+ "-" + employee.getProject()+ "-" +employee.getManager()) ;
			
		});
		
		return employeeList;
		
	}

	//PUT Request Call
	public boolean addEmployeeData(Employee ep) {
	
		 boolean status = false;
		 
		 try{
			 
			 String sql = "insert into employees(id,name,address,mobile_no,project,manager) values("+ep.getId()+",'"+ep.getName()+"','"+ep.getAddress()+"',"+ep.getMobile_no()+",'"+ep.getProject()+"','"+ep.getManager()+"')";
			 
			 template.execute(sql);
			 status=true;
		 }catch(Exception e) {
			 status = false;
		 }
		 return status;
		
	}

//DELETE CALL
	public boolean deleteEmployeeData(int id) {

		boolean status= false;
		
		try {
			String sql = "delete from employees where id ="+id;
		 
			template.execute(sql);
			
			status=true;
		}catch(Exception e) {
			
		 status = false;
	 }
	 return status;
	}



}
