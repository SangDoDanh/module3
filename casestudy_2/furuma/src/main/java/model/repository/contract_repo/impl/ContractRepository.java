package model.repository.contract_repo.impl;

import model.model.Contract;
import model.repository.Connection.DAO;
import model.repository.contract_repo.IContractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractRepository implements IContractRepository {
    @Override
    public List<Contract> getAll() {
        List<Contract> contractList = new ArrayList<>();
        Connection conn = DAO.getConnection();
        String sql = "Select * from contract;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                double deposit = rs.getDouble("deposit");
                int employeeID = rs.getInt("employee_id");
                int customerId = rs.getInt("customer_id");
                int facilityId = rs.getInt("facility_id");
                Contract contract = new Contract(id, startDate, endDate, deposit, employeeID, customerId, facilityId);
                contractList.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contractList;
    }

    @Override
    public Map<Integer, String> getEmployeeMap() {
        Map<Integer, String> employeeMap = new HashMap<>();
        Connection conn = DAO.getConnection();
        String sql = "select id, name from employee;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
                employeeMap.put(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeMap;
    }

    @Override
    public Map<Integer, String> getCustomerMap() {
        Map<Integer, String> customerMap = new HashMap<>();
        Connection conn = DAO.getConnection();
        String sql = "select id, name from customer;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                customerMap.put(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerMap;
    }

    @Override
    public Map<Integer, String> getFacilityMap() {
        Map<Integer, String> facilityMap = new HashMap<>();
        Connection conn = DAO.getConnection();
        String sql = "select id, name from facility;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                facilityMap.put(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilityMap;
    }

    @Override
    public Map<Integer, String> getAttachMapMap() {
        Map<Integer, String> attachMap = new HashMap<>();
        Connection conn = DAO.getConnection();
        String sql = "select c.id as id, af.name as name\n" +
                "from contract c\n" +
                "    join contract_detail cdt on c.id = cdt.contract_id\n" +
                "    join attach_facility af on cdt.attach_facility_id = af.id;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                attachMap.put(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attachMap;
    }
}
