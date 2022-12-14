package model.service.impl;

import model.model.Employee;
import model.repository.employee_repo.IEmployeeRepository;
import model.repository.employee_repo.impl.EmployeeRepository;
import model.service.IEmployeeService;

import java.util.List;

public class EmployeeService implements IEmployeeService {
    private static final IEmployeeRepository employeeRepository = new EmployeeRepository();

    @Override
    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    @Override
    public void delete(int idDelete) {
        employeeRepository.delete(idDelete);
    }

    @Override
    public void edit(int id, Employee employee) {
        employeeRepository.edit(id, employee);
    }
}
