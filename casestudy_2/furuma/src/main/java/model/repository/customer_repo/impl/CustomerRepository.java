package model.repository.customer_repo.impl;

import model.model.Customer;
import model.repository.Connection.DAO;
import model.repository.customer_repo.ICustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository implements ICustomerRepository {
    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        Connection conn = DAO.getConnection();
        String sql = "select * \n" +
                "from customer\n" +
                "where is_delete = false;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //id, customer_type_id, name, date_of_birth, gender,
            // id_card, phone_number, email, address
            while (rs.next()) {
                 int id = rs.getInt("id");
                 String name = rs.getString("name");
                 int customerTypeId = rs.getInt("customer_type_id");
                 Date Date = rs.getDate("date_of_birth");
                 int gender = rs.getInt("gender");
                 String idCard = rs.getString("id_card");
                 String phoneNumber = rs.getString("phone_number");
                 String email = rs.getString("email");
                 String address = rs.getString("address");
                 Customer customer = new Customer(id, name,customerTypeId, Date, gender, idCard, phoneNumber, email, address);
                 customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    @Override
    public Map<Integer, String> getCustomerTypeAll() {
        Map<Integer, String> customerTypeMap = new HashMap<>();
        Connection conn = DAO.getConnection();
        String sql = "select * from customer_type;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                customerTypeMap.put(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerTypeMap;
    }

    @Override
    public void delete(int id) {
        String sql = "update customer\n" +
                "set is_delete = true\n" +
                "where id = ?";
        Connection conn = DAO.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
