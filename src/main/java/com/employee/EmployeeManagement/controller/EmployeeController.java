package com.employee.EmployeeManagement.controller;


import com.employee.EmployeeManagement.dto.EmployeeReqDto;
import com.employee.EmployeeManagement.dto.EmployeeRespDto;
import com.employee.EmployeeManagement.entity.Employee;
import com.employee.EmployeeManagement.repo.EmployeeRepo;
import com.employee.EmployeeManagement.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    EmployeeServiceImpl service;

    @PostMapping("/add")
    public ResponseEntity<EmployeeRespDto> addEmp(@RequestBody EmployeeReqDto emp){
        EmployeeRespDto employee = service.addEmp(emp);
        return  ResponseEntity.ok(employee);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeRespDto>> getAllEmp(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "1") int pageSize){
        Pageable p = PageRequest.of(pageNo,pageSize);
        List<EmployeeRespDto> employee = service.getAllEmployees(p);
        return  ResponseEntity.ok(employee);
    }

    @GetMapping("/getEmp/{id}")
    public ResponseEntity<Employee> getEmp(@PathVariable Long id){
        Employee employee = service.getEmpById(id);
        return  ResponseEntity.ok(employee);
    }

    @GetMapping("/getname")
    public ResponseEntity<List<Employee>> getEmp(@RequestParam String name){
        List<Employee> employee =  service.getEmpByName(name);
        return  ResponseEntity.ok(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeRespDto> updateEmp(@RequestBody EmployeeReqDto employee,@PathVariable Long id){
        EmployeeRespDto employee1 = service.updateEmp(employee,id);

        return  ResponseEntity.ok(employee1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable Long id){
        String status = service.removeEmp(id);
        return  ResponseEntity.ok(status);
    }
}
