package com.yzn.springboot.crudapi.DAO;

import com.yzn.springboot.crudapi.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // Define a field for entityManager
    private EntityManager  entityManager;

    // Set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // Create a query
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee",Employee.class);

        // Execute query and get the result list

        // return the results
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {

        // Get the employee

        // return the employee
        return entityManager.find(Employee.class,id);
    }

    @Override
    public Employee save(Employee employee) {

        // Save employee
        // If id == 0 then insert/save else update the employee object
        Employee dbEmployee = entityManager.merge(employee);

        // Return dbEmployee it has updated id from the database(in the case of insert)
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        // Get the employee
        Employee employee = entityManager.find(Employee.class,id);

        // Remove the employee from the database
        entityManager.remove(employee);
    }
}
