package repository;

import model.User;

import java.util.List;

public interface IUserRepository {
    List<User> getAll();
    boolean insert(User user);
    boolean delete(int userId);
    boolean update(int userId, User user);
    User getUserById(int id);

    List<User> getAllByName();

    List<User> getUserByName(String search);
}
