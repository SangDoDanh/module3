package model.repository.division_repo.impl;

import model.repository.Connection.DAO;
import model.repository.division_repo.IDivisionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DivisionRepository implements IDivisionRepository {
    @Override
    public Map<Integer, String> getAll() {
        Map<Integer, String> result = new HashMap<>();
        String sql = "select * from division;";
        Connection conn = DAO.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                result.put(id, name);
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

        return result;
    }
}
