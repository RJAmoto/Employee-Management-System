package com.rudolphjohn.restfulwebservices.employee;

import com.rudolphjohn.restfulwebservices.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    public final EmployeeManager service;

    public Controller(EmployeeManager service) {
        this.service = service;
    }

    //retrieve all employees
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return service.findEmployees();
    }

    //retrieve employee by name
    @GetMapping("/employees/byName/{name}")
    public Employee getEmpByName(@PathVariable String name){
        Employee employee = service.getEmpByName(name);
        if(employee==null) throw new ResourceNotFoundException("name -"+name);
        return employee;
    }

    //retrieve employee by id
    @GetMapping("/employees/byID/{id}")
    public Employee getEmpById(@PathVariable Integer id){
         return service.findOne(id);
    }
    //add Employee
    @PostMapping("/employees")
    public void createUser(@RequestBody Employee employee){
        service.save(employee);
    }


    //update employee
   @PutMapping("/employees")
    public void updateEmployee(@RequestBody Employee employee){
        service.save(employee);
    }
    //delete employee
    //@DeleteMapping("/employees/deleteByname/{name}")
    //public void deleteEmployee(@PathVariable(value = "ID") String name){
    //  service.removeEmp(name);
    //}

    @DeleteMapping("/employees/deleteById/{id}")
    public void deleteEmployee(@PathVariable int id){
        service.removeEmp(id);
    }

    @GetMapping(path = "/employees/getByPage/{pageNo}/{pageSize}")
    public List<Employee> pageEmployee(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return service.employeePage(pageNo, pageSize);
    }
    @GetMapping(path = "/employees/sorted/{field}")
    public List<Employee> pageEmployeeSort(@PathVariable String field) {
        return service.employeePageSort(field);
    }
}

