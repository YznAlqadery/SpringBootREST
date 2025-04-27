package com.yzn.springboot.crudapi.DAO;

import com.yzn.springboot.crudapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// JpaRepository<EntityType, Primary Key>
// http://localhost:8080/api/members
//@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    // No need to write any code here
}
