package com.rudolphjohn.restfulwebservices.employee;

import com.rudolphjohn.restfulwebservices.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    public final EmployeeManager service;

    public Controller(EmployeeManager service) {
        this.service = service;
    }

    //retrieve all employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        //return service.findEmployees();
        return new ResponseEntity<>(service.findEmployees(), HttpStatus.OK);
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
    @PostMapping("/employees/add")
    public ResponseEntity<Employee> createUser(@RequestBody Employee employee){

        Employee emp = service.save(employee);

        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }

    //update employee
   @PutMapping("/employees/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee emp = service.update(employee);
        return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
    }
    //delete employee
    //@DeleteMapping("/employees/deleteByname/{name}")
    //public void deleteEmployee(@PathVariable(value = "ID") String name){
    //  service.removeEmp(name);
    //}
    @DeleteMapping("/employees/deleteById/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id){
       service.removeEmp(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/employees/getByPage/{pageNo}/{pageSize}")
    public List<Employee> pageEmployee(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return service.employeePage(pageNo, pageSize);
    }

    @GetMapping("/employees/sorted/{field}")
    public List<Employee> pageEmployeeSort(@PathVariable String field) {
        return service.employeePageSort(field);
    }
}

