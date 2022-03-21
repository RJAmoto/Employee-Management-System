package com.rudolphjohn.casestudy.webservices.restfulwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//user DAO service
@Service
public class EmployeeManager {


    public final EmployeeDao repository;

    @Autowired
    public EmployeeManager(EmployeeDao repository) {
        this.repository = repository;
    }
    int id = 0;

    //get list employee
    public List<Employee> findAll(){
        return repository.findAll();
    }

    //get employee by name
    /*public Employee getEmpByName(String name){
        Employee emp = null;

        for(int a = 0; a < empList.size(); a++){
            if((empList.get(a).getFirstName()).equals(name)||
                    (empList.get(a).getLastName()).equals(name)||
                    ((empList.get(a).getFirstName())+" "+(empList.get(a).getLastName())).equals(name)){
                emp = empList.get(a);
            }
        }
        return emp;
    }*/
    //get Employee by id
   /* public Employee findOne(int id){

        for(int a = 0; a< empList.size(); a++){
            if(empList.get(a).getID()==id){
                return empList.get(a);
            }
        }
        return null;
    }*/

    //Add employee
    public void save(Employee employee1) {
        Optional<Employee> employee2 = repository.findByFirstname(employee1.getFirstName());
        if (employee2.isPresent()) {
            throw new IllegalStateException("Already have this employee " + employee2);
        }
        repository.save(employee1);
        System.out.println("Employee data: "+ employee1);
    }

    //Update Employee

    //Delete Employee
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
    //Pagination

    /*public List<Employee> employeePage(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Employee> pagedResult = this.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }*/
}

