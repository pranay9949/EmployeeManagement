package com.employee.EmployeeManagement.service;


import com.employee.EmployeeManagement.dto.EmployeeReqDto;
import com.employee.EmployeeManagement.dto.EmployeeRespDto;
import com.employee.EmployeeManagement.entity.Employee;
import com.employee.EmployeeManagement.repo.EmployeeRepo;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo){
        this.employeeRepo=employeeRepo;
    }

    @Override
    public EmployeeRespDto addEmp(EmployeeReqDto employee) {
        Employee emp = new Employee();
        BeanUtils.copyProperties(employee,emp);
       Employee employee1 =  employeeRepo.save(emp);
       EmployeeRespDto dto = new EmployeeRespDto();
       BeanUtils.copyProperties(employee1,dto);
        return dto;
    }

    @Override
    public List<EmployeeRespDto> getAllEmployees(Pageable pageable) {
        Page<Employee> p1 = employeeRepo.findAll(pageable);
        List<Employee> employeeList = p1.getContent();
        List<EmployeeRespDto> dto = new ArrayList<>();
        for(Employee e : employeeList){
            EmployeeRespDto d1 = new EmployeeRespDto();
            BeanUtils.copyProperties(e,d1);
            dto.add(d1);


        }
        return dto;
    }

    @Override
    public Employee getEmpById(Long id) {

       Optional<Employee> emp = employeeRepo.findById(id);
       Employee employee=null;
       if(emp.isPresent()){
           employee = emp.get();
       }

        return employee;
    }

    @Override
    public List<Employee> getEmpByName(String name) {
        List<Employee> emp = employeeRepo.findByName(name);
        return emp;
    }

    @Override
    public EmployeeRespDto updateEmp(EmployeeReqDto employee, Long id) {
        Optional<Employee> optEmp = employeeRepo.findById(id);
        Employee emp = null;
        if(optEmp.isPresent()){
            emp=optEmp.get();
        }
        if(employee.getAddress()!=null && (!employee.getAddress().isEmpty())) {
            emp.setAddress(employee.getAddress());
        }
        if(employee.getName()!=null && (!employee.getName().isEmpty())) {
            emp.setName(employee.getName());
        }
        if(employee.getSalary()!=null ) {
           emp.setSalary(employee.getSalary());
        }
        if(employee.getRole()!=null ) {
            emp.setRole(employee.getRole());
        }
     Employee e1 = employeeRepo.save(emp);
        EmployeeRespDto dto = new EmployeeRespDto();
        BeanUtils.copyProperties(e1,dto);

        return dto;

    }

    @Override
    public String removeEmp(Long id) {
        Optional<Employee> opEmp = employeeRepo.findById(id);
        Employee employeee;
        String delete="Not deleted";
        if(opEmp.isPresent()){
            employeeRepo.deleteById(id);
            delete = "Deleted";
        }

        return delete;

    }
}
