package com.yzn.springboot.crudapi.rest;

import com.yzn.springboot.crudapi.entity.Employee;
import com.yzn.springboot.crudapi.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;


    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // Expose "/employees" endpoint and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // Expose GET "/employees/{employeeId}" endpoint for retrieving a single employee
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){

        Employee employee = employeeService.findById(employeeId);

        if(employee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return employee;
    }
}
