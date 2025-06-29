package com.employee.EmployeeManagement.repo;

import ch.qos.logback.core.model.conditional.ElseModel;
import com.employee.EmployeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    List<Employee> findByName(String name);
}
