package com.yzn.springboot.crudapi.DAO;

import com.yzn.springboot.crudapi.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
