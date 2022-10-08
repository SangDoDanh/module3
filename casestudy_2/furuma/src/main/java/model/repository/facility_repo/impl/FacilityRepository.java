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
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int are = rs.getInt("area");
                double cost = rs.getDouble("cost");
                int maxPeople = rs.getInt("max_people");
                int rentTypeId = rs.getInt("rent_type_id");
                int facilityTypeId = rs.getInt("facility_type_id");
                String standardRoom = rs.getString("standard_room");
                String descriptionOtherConvenience = rs.getString("description_other_convenience");
                double poolArea = rs.getDouble("pool_area");
                int numberOfFloors = rs.getInt("number_of_floors");
                String facilityFree = rs.getString("facility_free");
                Facility facility = new Facility(id, name,are,cost,maxPeople,rentTypeId,facilityTypeId,standardRoom,descriptionOtherConvenience,poolArea,numberOfFloors,facilityFree);
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
