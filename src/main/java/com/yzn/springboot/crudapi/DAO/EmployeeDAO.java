package com.yzn.springboot.crudapi.DAO;

import com.yzn.springboot.crudapi.Entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
}
