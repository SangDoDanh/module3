package model.repository.employee_repo;

import model.model.Employee;

import java.util.List;

public interface IEmployeeRepository {
    List<Employee> getAll();
    boolean delete(int id);
    boolean update(Employee employee);

    void edit(int id, Employee employee);
}
