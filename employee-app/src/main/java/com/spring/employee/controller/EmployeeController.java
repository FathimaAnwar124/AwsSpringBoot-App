package com.spring.employee.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.employee.model.Employee;
import com.spring.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	public EmployeeService service;
	
	//GET
	@RequestMapping(value="/employee-info",method= RequestMethod.GET)
	public List<Employee>getEmployeeInfo(){
		
		List<Employee> li = service.getEmployeeData(); 
		
		return li;
	}
	
	// POST

	@RequestMapping(value="/employee-info",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee employeePostCall(@RequestBody Employee employee)  {
		
		
		//validation
		if(Objects.isNull(employee.getId()) || (employee.getId()==0)){
			throw new IllegalArgumentException("course id is mandatory or invalid argument passed");
		}
		
		//service
		
		Employee Obj = service.getEmployeeDataBasedOnId(employee.getId());
		
		return Obj;
	}
	
	
	//PUT
	
	@RequestMapping(value="/employee-info",method=RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public String employeePutCall(@RequestBody Employee ep) {
		
		boolean status =service.addEmployeeData(ep);
		
		if(status) {
			return "Successfully added";
		}
		
		else {
			
			return "Some failures occures";
		}
		
	}
	
	//DELETE
	@RequestMapping(value = "/deleteEmployeeData/{id}",method=RequestMethod.DELETE)
	
	
    public String getMessage(@PathVariable("id") int id) {
        
        boolean msg =  service.deleteEmployeeData(id);
        		
        if(msg) {
        	return "Successfully completed";
    }
        else {
        	return "Failed";
        }
	

}

}
