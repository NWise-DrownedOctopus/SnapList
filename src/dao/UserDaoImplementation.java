package dao;

import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDaoImplementation implements UserDaoInterface {

    private long nextId = 1L;
    private final List<User> users = new ArrayList<User>();

    @Override
    public long addUser(User user) {
        long id = nextId++;
        user.setId(id);
        users.add(user);
        return id;
    }

    // @Override
    // public Optional<User> findById(long id) {

    // }

    // @Override
    // public Optional<User> findByUserName(String name) {

    // }
}
