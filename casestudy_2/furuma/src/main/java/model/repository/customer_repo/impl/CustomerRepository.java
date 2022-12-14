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
        String sql = "select * from customer\n" +
                "where customer.is_delete = false;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
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

    @Override
    public void create(Customer customer) {
        String sql = "insert into customer(customer_type_id, name, date_of_birth, gender, id_card, phone_number, email, address) \n" +
                "values(?, ?, ?, ?, ?, ?, ?, ?);";
        Connection conn = DAO.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer.getCustomerTypeId());
            ps.setString(2, customer.getName());
            ps.setDate(3,customer.getDate());
            ps.setInt(4,customer.getGender());
            ps.setString(5,customer.getIdCard());
            ps.setString(6,customer.getPhoneNumber());
            ps.setString(7,customer.getEmail());
            ps.setString(8,customer.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {

        String sql = "update customer\n" +
                "set \n" +
                "customer_type_id = ?,\n" +
                " name = ?,\n" +
                " date_of_birth = ?,\n" +
                " gender = ?,\n" +
                " id_card = ?,\n" +
                " phone_number = ?,\n" +
                " email = ?,\n" +
                " address = ?\n" +
                "where id = ?;";
        Connection conn = DAO.getConnection();
        try {
            // customer_type_id, name, date_of_birth, gender,
            // id_card, phone_number, email, address,id
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer.getCustomerTypeId());
            ps.setString(2, customer.getName());
            ps.setDate(3,customer.getDate());
            ps.setInt(4,customer.getGender());
            ps.setString(5,customer.getIdCard());
            ps.setString(6,customer.getPhoneNumber());
            ps.setString(7,customer.getEmail());
            ps.setString(8,customer.getAddress());
            ps.setInt(9,customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> search(String keySearch, String customerTypeSearch) {
        List<Customer> customerListSearch = new ArrayList<>();
        Connection conn = DAO.getConnection();
        String sql = "select c.* \n" +
                "from customer as c \n" +
                "join customer_type as ct on c.customer_type_id = ct.id\n" +
                "where c.name like ? and ct.name like ? and c.is_delete = false;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+keySearch+"%");
            ps.setString(2, "%"+customerTypeSearch+"%");
            ResultSet rs = ps.executeQuery();
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
                customerListSearch.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerListSearch;
    }



    @Override
    public List<Customer> search(String keySearch, String customerTypeSearch, int genderSeach) {
        List<Customer> customerListSearch = new ArrayList<>();
        Connection conn = DAO.getConnection();
        String sql = "select c.* \n" +
                "from customer as c \n" +
                "join customer_type as ct on c.customer_type_id = ct.id\n" +
                "where c.name like ? and ct.name like ? and c.is_delete = false and c.gender = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+keySearch+"%");
            ps.setString(2, "%"+customerTypeSearch+"%");
            ps.setInt(3, genderSeach);
            ResultSet rs = ps.executeQuery();
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
                customerListSearch.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerListSearch;
    }

    @Override
    public int getCountPage() {
        Connection conn = DAO.getConnection();
        int result = 0;
        String sql = "select count(*) as count from customer\n" +
                "where customer.is_delete = false;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Customer> getAll(int indexPage, int amountCustomer) {
        List<Customer> customerList = new ArrayList<>();
        Connection conn = DAO.getConnection();
        String sql = "select * from customer\n" +
                "where customer.is_delete = false\n" +
                "limit ?,?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, (indexPage - 1) * amountCustomer);
            ps.setInt(2, amountCustomer);
            ResultSet rs = ps.executeQuery();
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
}
