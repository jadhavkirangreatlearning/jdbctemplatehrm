package com.csi.service;

import com.csi.dao.EmployeeDao;
import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDaoImpl;

    @Override
    public void signUp(Employee employee) {
        employeeDaoImpl.signUp(employee);
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        return employeeDaoImpl.signIn(empEmailId, empPassword);
    }

    @Override
    public void saveAllData(List<Employee> employeeList) {
        employeeDaoImpl.saveAllData(employeeList);
    }

    @Override
    public Employee findById(int empId) {
        return employeeDaoImpl.findById(empId);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDaoImpl.findAll();
    }

    @Override
    public void update(int empId, Employee employee) {
        employeeDaoImpl.update(empId, employee);
    }

    @Override
    public void deleteById(int empId) {
        employeeDaoImpl.deleteById(empId);
    }

    @Override
    public void deleteAll() {
        employeeDaoImpl.deleteAll();
    }
}
