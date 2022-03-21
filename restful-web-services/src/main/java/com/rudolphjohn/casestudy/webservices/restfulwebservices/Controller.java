package com.rudolphjohn.casestudy.webservices.restfulwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    public final EmployeeManager service;

    @Autowired
    public Controller(EmployeeManager service) {
        this.service = service;
    }

    //retrieve all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return service.findAll();
    }
    //retrieve employee by name
    /*@GetMapping("/employees/byName/{name}")
    public Employee getEmpByName(@PathVariable String name){
        Employee employee = .getEmpByName(name);
        if(employee==null) throw new ResourceNotFoundException("name -"+name);
       return employee;
    }*/

    //retrieve employee by id
   // @GetMapping("/employees/byID/{id}")
   // public Employee getEmpById(@PathVariable Integer id){
   //     return manager.findOne(id);
    //}
    //add Employee
    @PostMapping("/employees")
    public void createUser(@RequestBody Employee employee){
        service.save(employee);
    }


    //update employee
   /* @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer id, @RequestBody Employee employee){
        Employee employee1 = employeeRepository.getById(id);
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setEmail(employee.getEmail());

        return ResponseEntity.ok(this.employeeRepository.save(employee1));
    }*/

    //delete employee
    //@DeleteMapping("/employees/{name}")
    //public void deleteEmployee(@PathVariable(value = "ID") String name){
     //   manager.removeEmp(name);
    //}

    //@DeleteMapping("/employees/{id}")

}

