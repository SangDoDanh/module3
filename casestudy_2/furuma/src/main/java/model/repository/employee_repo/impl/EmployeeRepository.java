package model.repository.employee_repo.impl;

import model.model.Employee;
import model.repository.Connection.DAO;
import model.repository.employee_repo.IEmployeeRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository  implements IEmployeeRepository {
    @Override
    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "call SELECT_ALL_EMPLOYEE();";

        Connection conn = DAO.getConnection();
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //id, name, date_of_birth, id_card, salary, phone_number, email, address,
                // position_id, education_degree_id, division_id, user_name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date dayOfBirth = rs.getDate("date_of_birth");
                String idCard = rs.getString("id_card");
                double salary = rs.getDouble("salary");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int positionId = rs.getInt("position_id");
                int educationDegreeId = rs.getInt("education_degree_id");
                int divisionId = rs.getInt("division_id");
                String userName = rs.getString("user_name");
                employeeList.add(new Employee(id, name,dayOfBirth,idCard,salary,phoneNumber,email,address,positionId,educationDegreeId,divisionId,userName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employeeList;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(Employee employee) {
        return false;
    }
}
