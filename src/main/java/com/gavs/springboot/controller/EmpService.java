package com.gavs.springboot.controller;



import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gavs.springboot.model.Employee;
import com.gavs.springboot.model.EmployeeDAO;

@RestController
public class EmpService {
		
    @RequestMapping(value="/findEmployee",method= RequestMethod.POST)
    public Employee homepage(@RequestBody Employee emp) { 
        EmployeeDAO dao=new EmployeeDAO();
        return dao.findEmployee(emp);
    }
    @RequestMapping(value="/addEmployee",method= RequestMethod.POST)
    public int addEmployee(@RequestBody	Employee emp) {
    	EmployeeDAO empp = new EmployeeDAO();
    	return empp.addEmployee(emp);
    }
    
    @RequestMapping(value="/updateEmployee",method= RequestMethod.PUT)
    public int modifyEmployee(@RequestBody	Employee emp) {
    	EmployeeDAO empp = new EmployeeDAO();
    	return empp.modifyEmployee(emp);
    }
    @RequestMapping(value="/removeEmployee",method= RequestMethod.DELETE)
    public int removeEmployee(@RequestBody	Employee emp) {
    	EmployeeDAO empp = new EmployeeDAO();
    	return empp.deleteEmployee(emp);
    }
    
    @RequestMapping("/showEmployee") 
    public List<Employee> welcomepage() {
		EmployeeDAO dao=new EmployeeDAO();
        try {
			return dao.fetchEmployeeDAO();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }

}
