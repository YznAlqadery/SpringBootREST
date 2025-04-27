package com.yzn.springboot.crudapi.service;

import com.yzn.springboot.crudapi.DAO.EmployeeRepository;
import com.yzn.springboot.crudapi.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        // "Optional" is a different pattern instead of having to check for nulls
        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee = null;

        if(result.isPresent()){
          employee = result.get();
        }
        else{
            // We didn't find the employee
            throw new RuntimeException("Didn't find employee of id - " + id);
        }
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
