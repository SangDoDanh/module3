package model.service;

import model.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAll();

    void delete(int idDelete);

    void edit(int id, Employee employee);
}
