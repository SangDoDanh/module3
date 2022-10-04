package repository.impl;

import model.User;
import repository.IUserRepository;
import repository.dao.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private static final String INERT_USER = "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";
    private static final String DELETE_USER = "call sp_delete_user(?);";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?;";
    private static final String SELECT_ALL_BY_NAME = "SELECT * FROM users\n" +
            "order by name;";
    private static final String SEARCH_ALL_BY_NAME = "select * from users\n" +
            "where name like ?;";

    private static final String UPDATE = "call sp_update_user(?, ?, ?, ?);";
    private static final String SP_GET_ALL = "call sp_show_all_user();";
    private static final Connection connection = new ConnectionDB().getConnection();
    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try{
            PreparedStatement psUser = connection.prepareCall(SP_GET_ALL);
            ResultSet rs = psUser.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                userList.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUserById(int id) {
        try {
            PreparedStatement psUser = connection.prepareStatement(SELECT_BY_ID);
            psUser.setInt(1, id);
            ResultSet rs = psUser.executeQuery();
            User user = null;
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public List<User> getAllByName() {
        List<User> userList = new ArrayList<>();
        try{
            PreparedStatement psUser = connection.prepareStatement(SELECT_ALL_BY_NAME);
            ResultSet rs = psUser.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                userList.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> getUserByName(String search) {

        List<User> userList = new ArrayList<>();
        try{
            PreparedStatement psUser = connection.prepareStatement(SEARCH_ALL_BY_NAME);
            psUser.setString(1, "%"+search+"%");
            ResultSet rs = psUser.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                userList.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean insert(User user) {
        try {
            PreparedStatement psUser = connection.prepareStatement(INERT_USER);
            psUser.setString(1, user.getName());
            psUser.setString(2, user.getEmail());
            psUser.setString(3, user.getCountry());
            psUser.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int userId) {
        if(getUserById(userId) != null) {
            try {
                PreparedStatement psUser = connection.prepareCall(DELETE_USER);
                psUser.setInt(1, userId);
                psUser.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean update(int userId, User user) {
        try {
            PreparedStatement psUser = connection.prepareCall(UPDATE);
            psUser.setInt(1, user.getId());
            psUser.setString(2, user.getName());
            psUser.setString(3, user.getEmail());
            psUser.setString(4, user.getCountry());
            psUser.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
