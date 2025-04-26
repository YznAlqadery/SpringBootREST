package com.yzn.springboot.crudapi.rest;

import com.yzn.springboot.crudapi.entity.Employee;
import com.yzn.springboot.crudapi.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

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

    // Expose POST mapping for "/employees - add a new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){

        // Also just in case they pass an id, set it to 0
        // this is to force a save of the employee object instead of an update
        employee.setId(0);

        // Return the saved/updated employee
        return employeeService.save(employee);
    }
}
