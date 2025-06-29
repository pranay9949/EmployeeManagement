package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.EmployeeReqDto;
import com.employee.EmployeeManagement.dto.EmployeeRespDto;
import com.employee.EmployeeManagement.entity.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    public EmployeeRespDto addEmp(EmployeeReqDto employee);

    public List<EmployeeRespDto> getAllEmployees(Pageable pageable);

    public Employee getEmpById(Long id);


    public  List<Employee> getEmpByName(String name);


    public EmployeeRespDto updateEmp(EmployeeReqDto employee,Long id);


    public String removeEmp(Long id);


}
