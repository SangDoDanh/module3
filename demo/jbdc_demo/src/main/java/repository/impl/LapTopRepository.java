package repository.impl;

import model.LapTop;
import repository.IRepository;
import repository.RepositoryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LapTopRepository implements IRepository<LapTop> {

    @Override
    public int insert(LapTop lapTop) {
        Connection conn = RepositoryUtil.getConnection();
        String sql = "insert into laptop(name, producer, price, color) \n" +
                "values\n" +
                "(?, ?, ?, ?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,lapTop.getName());
            ps.setString(2,lapTop.getProducer());
            ps.setDouble(3,lapTop.getPrice());
            ps.setString(4,lapTop.getColor());
            int numberRowInsert =  ps.executeUpdate();
            RepositoryUtil.closeConnection(conn);
            return numberRowInsert;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int delete(int id) {
        Connection conn = RepositoryUtil.getConnection();
        String sql = "delete from laptop where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int numberRowDelete =  ps.executeUpdate();
            RepositoryUtil.closeConnection(conn);
            return numberRowDelete;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(LapTop lapTop) {
        Connection conn = RepositoryUtil.getConnection();
        String sql = "update laptop \n" +
                "set name = ?, producer = ?, price = ?, color = ?\n" +
                "where id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, lapTop.getName());
            ps.setString(2, lapTop.getProducer());
            ps.setDouble(3, lapTop.getPrice());
            ps.setString(4, lapTop.getColor());
            ps.setInt(5, lapTop.getId());
            int numberRowDelete =  ps.executeUpdate();
            RepositoryUtil.closeConnection(conn);
            return numberRowDelete;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<LapTop> select() {
        return null;
    }

    @Override
    public List<LapTop> select(String condition) {
        return null;
    }
}
