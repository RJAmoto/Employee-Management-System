package com.rudolphjohn.restfulwebservices.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{
    Optional<Employee> findByFirstName(String firstName);
    Optional<Employee> findEmployeeByEmail(String email);
}