package service;

import model.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    User getUserById(int id);

    void update(int id, User user);

    boolean add(User user);

    void delete(int id);

    List<User> sortByName();

    List<User> search(String search);
}
