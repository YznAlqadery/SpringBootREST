package com.yzn.springboot.crudapi.service;

import com.yzn.springboot.crudapi.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
