package service.impl;

import model.User;
import repository.IUserRepository;
import repository.impl.UserRepository;
import service.IUserService;

import java.util.List;

public class UserService implements IUserService {
    private static IUserRepository userRepository = new UserRepository();
    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void update(int id, User user) {
        userRepository.update(id, user);
    }

    @Override
    public boolean add(User user) {
        return userRepository.insert(user);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> sortByName() {
         return  userRepository.getAllByName();
    }

    @Override
    public List<User> search(String search) {
        return userRepository.getUserByName(search);
    }
}
