package com.rudolphjohn.restfulwebservices.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//user DAO service
@Service
public class EmployeeManager {
    public final EmployeeDao repository;

    public EmployeeManager(EmployeeDao repository) {
        this.repository = repository;
    }


    //get list employee
    public List<Employee> findEmployees(){
        return repository.findAll();
    }

    //get employee by name
    public Employee getEmpByName(String name){
        List<Employee> empList = repository.findAll();
        Employee emp = null;

        for(int a = 0; a < empList.size(); a++){
            if((empList.get(a).getFirstName()).equals(name)||
                    (empList.get(a).getLastName()).equals(name)||
                    ((empList.get(a).getFirstName())+" "+(empList.get(a).getLastName())).equals(name)){
                emp = empList.get(a);
            }
        }
        return emp;
    }

    //get Employee by id
    public Employee findOne(int id){
        return repository.getById(id);
    }
    //Add employee
    public void save(Employee employee1) {
        Optional<Employee> employee2 = repository.findByFirstName(employee1.getFirstName());
        if (employee2.isPresent()) {
            throw new IllegalStateException("Already have this employee " + employee2);
        }
        repository.save(employee1);
        System.out.println("Employee data: "+ employee1);
    }

    //Update Employee

    //Delete Employee
    public void removeEmp(int id){

            boolean checkEmployee = repository.existsById(id);

            if (checkEmployee) {
                repository.deleteById(id);
            }
            throw new IllegalStateException("Employee not here");
    }
    /*public Employee removeEmp(String name){

        Iterator<Employee> iterator = empList.iterator();

        while(iterator.hasNext()){
            Employee emp = iterator.next();

            if(((emp.getFirstName()).equals(name)||
                    (emp.getLastName()).equals(name)||
                    ((emp.getFirstName())+" "+(emp.getLastName())).equals(name))){
                iterator.remove();
                --id;
                return emp;
            }
        }
        return null;
    }*/

    //Pagination with sort
    public List<Employee> employeePageSort(String field) {
        return repository.findAll(Sort.by(field));
    }
    //pagination
    public List<Employee> employeePage(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Employee> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}

