package model.repository.facility_repo.impl;

import model.model.Facility;
import model.repository.Connection.DAO;
import model.repository.facility_repo.IFacilityRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacilityRepository implements IFacilityRepository {
    @Override
    public List<Facility> getAll() {
        Connection connection = DAO.getConnection();
        List<Facility> facilityList = new ArrayList<>();
        String sql = "select * from facility;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //id, name, area, cost, max_people, rent_type_id, facility_type_id, standard_room,
            // description_other_convenience, pool_area, number_of_floors, facility_free
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int are = rs.getInt("area");
                double cost = rs.getDouble("cost");
                int maxPeople = rs.getInt("max_people");
                int rent_type_id = rs.getInt("rent_type_id");
                int facility_type_id = rs.getInt("facility_type_id");
                String standard_room = rs.getString("standard_room");
                String description_other_convenience = rs.getString("description_other_convenience");
                double pool_area = rs.getDouble("pool_area");
                int number_of_floors = rs.getInt("number_of_floors");
                String facility_free = rs.getString("facility_free");
                Facility facility = new Facility(id, name,are,cost,maxPeople,rent_type_id,facility_type_id,standard_room,description_other_convenience,pool_area,number_of_floors,facility_free);
                facilityList.add(facility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilityList;
    }

    @Override
    public Map<Integer, String> getFacilityTypeAll() {
        Connection connection = DAO.getConnection();
        Map<Integer, String> facilityTypeMap = new HashMap<>();
        String sql = "select * from facility_type;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //id, name, area, cost, max_people, rent_type_id, facility_type_id, standard_room,
            // description_other_convenience, pool_area, number_of_floors, facility_free
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                facilityTypeMap.put(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilityTypeMap;
    }

    @Override
    public Map<Integer, String> getrentTypeMapAll() {
        Connection connection = DAO.getConnection();
        Map<Integer, String> rentTypeMap = new HashMap<>();
        String sql = "select *from rent_type;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //id, name, area, cost, max_people, rent_type_id, facility_type_id, standard_room,
            // description_other_convenience, pool_area, number_of_floors, facility_free
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                rentTypeMap.put(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentTypeMap;
    }
}
