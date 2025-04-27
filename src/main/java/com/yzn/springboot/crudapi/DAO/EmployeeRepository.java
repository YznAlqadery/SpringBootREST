package com.yzn.springboot.crudapi.DAO;

import com.yzn.springboot.crudapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<EntityType, Primary Key>
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    // No need to write any code here
}
