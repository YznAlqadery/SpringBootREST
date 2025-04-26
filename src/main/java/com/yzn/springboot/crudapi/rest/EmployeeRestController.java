package com.yzn.springboot.crudapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yzn.springboot.crudapi.entity.Employee;
import com.yzn.springboot.crudapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    private ObjectMapper objectMapper;


    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper){
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
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

    // Expose POST mapping for "/employees" - add a new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){

        // Also just in case they pass an id, set it to 0
        // this is to force a save of the employee object instead of an update
        employee.setId(0);

        // Return the saved/updated employee
        return employeeService.save(employee);
    }

    // Expose PUT Mapping for "/employees" - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    // Expose PATCH Mapping for "/employees/{employeeId}" - patch employee (partial update)
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String,Object> patchPayload){

        // Retrieve the employee from the database
        Employee employee = employeeService.findById(employeeId);

        // Throw exception if not found
        if(employee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        // Throw exception if the request body contains "id" key
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Employee id not allowed in request body - " + employeeId);
        }

        Employee patchedEmployee = apply(patchPayload,employee);

        // Since the "id" is not 0 it will be updated
        return employeeService.save(patchedEmployee);
    }

    private Employee apply(Map<String, Object> patchPayload, Employee employee) {

        // Convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(employee,ObjectNode.class);

        // Convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload,ObjectNode.class);

        // Merge the patch updates into the employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode,Employee.class);
    }
}
