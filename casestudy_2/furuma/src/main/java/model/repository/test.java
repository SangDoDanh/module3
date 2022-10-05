package model.repository;

import model.model.Employee;
import model.repository.Connection.DAO;
import model.service.IEmployeeService;
import model.service.impl.EmployeeService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class test {
    public static void main(String[] args) {
        IEmployeeService employeeService = new EmployeeService();
        Connection conn = DAO.getConnection();
        List<Employee> employeeList = employeeService.getAll();
        System.out.println("Employee count: " + employeeList.size());
    }
}
