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
        String sql = "update employee\n" +
                "set is_remove = true\n" +
                "where id = ?;";
        Connection conn = DAO.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean update(Employee employee) {
        return false;
    }

    @Override
    public void edit(int id, Employee employee) {
        Connection connection = DAO.getConnection();
        String sql = "update employee\n" +
                "set  \n" +
                "name =?, date_of_birth = ?, id_card = ?, salary = ?, phone_number = ?, \n" +
                "email = ?, address = ?, position_id = ?, education_degree_id = ?, division_id = ?\n" +
                "where id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,employee.getName());
            ps.setDate(2, employee.getDateOfBirth());
            ps.setString(3, employee.getIdCard());
            ps.setDouble(4, employee.getSalary());
            ps.setString(5, employee.getPhoneNumber());
            ps.setString(6, employee.getEmail());
            ps.setString(7, employee.getAddress());
            ps.setInt(8, employee.getPositionId());
            ps.setInt(9, employee.getEducationDegreeId());
            ps.setInt(10, employee.getDivisionId());
            ps.setInt(11,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
