package com.csi.dao;

import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    String INSERT_SQL = "insert into employee(empid, empname, empaddress, empsalary, empcontactnumber,empdob, empemailid, emppassword)values(?, ?, ?, ?, ?, ?, ?, ?)";

    String SELECT_ALL_SQL = "select * from employee";

    String SELECT_BY_ID_SQL = "select * from employee where empid=?";

    String UPDATE_SQL = "update employee set empname=?, empaddress=?, empsalary=?, empcontactnumber=?, empdob=?, empemailid=?, emppassword=? where empid=?";

    String DELETE_BY_ID_SQL = "delete from employee where empid=?";

    String TRUNCATE_SQL = "truncate table employee";

    private Employee employee(ResultSet resultSet, int numRow) throws SQLException {
        return Employee.builder().empId(resultSet.getInt(1)).empName(resultSet.getString(2)).empAddress(resultSet.getString(3)).empSalary(resultSet.getDouble(4)).empContactNumber(resultSet.getLong(5)).empDOB(resultSet.getDate(6)).empEmailId(resultSet.getString(7)).empPassword(resultSet.getString(8)).build();
    }

    @Override
    public void signUp(Employee employee) {

        jdbcTemplate.update(INSERT_SQL, employee.getEmpId(), employee.getEmpName(), employee.getEmpAddress(), employee.getEmpSalary(), employee.getEmpContactNumber(), employee.getEmpDOB(), employee.getEmpEmailId(), employee.getEmpPassword());

    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        boolean flag = false;

        for (Employee employee : findAll()) {
            if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void saveAllData(List<Employee> employeeList) {

        for (Employee employee : employeeList) {
            jdbcTemplate.update(INSERT_SQL, employee.getEmpId(), employee.getEmpName(), employee.getEmpAddress(), employee.getEmpSalary(), employee.getEmpContactNumber(), employee.getEmpDOB(), employee.getEmpEmailId(), employee.getEmpPassword());
        }
    }

    @Override
    public Employee findById(int empId) {
        return jdbcTemplate.query(SELECT_BY_ID_SQL, this::employee, empId).get(0);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, this::employee);
    }

    @Override
    public void update(int empId, Employee employee) {
        jdbcTemplate.update(UPDATE_SQL, employee.getEmpName(), employee.getEmpAddress(), employee.getEmpSalary(), employee.getEmpContactNumber(), employee.getEmpDOB(), employee.getEmpEmailId(), employee.getEmpPassword(), empId);

    }

    @Override
    public void deleteById(int empId) {
        jdbcTemplate.update(DELETE_BY_ID_SQL, empId);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(TRUNCATE_SQL);
    }
}
