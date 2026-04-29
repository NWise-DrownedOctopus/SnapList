package dao;

import model.User;
import java.util.Optional;

public interface UserDaoInterface {
    long addUser(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findById(long id);
}