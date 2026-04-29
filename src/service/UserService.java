package service;

import dao.UserDaoImplementation;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class UserService {
    private final UserDaoImplementation dao;

    public UserService(UserDaoImplementation dao) {
        this.dao = dao;
    }

    public User registerUser(String username, String plainTextPassword) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (plainTextPassword == null || plainTextPassword.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }

        String hash = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());

        User newUser = new User();
        newUser.setUsername(username.trim());
        newUser.setPasswordHash(hash);
        newUser.setAdmin(false);

        dao.addUser(newUser);
        return newUser;
    }

    public Optional<User> login(String username, String plainTextPassword) {
        Optional<User> found = dao.findByUsername(username);

        if (found.isEmpty()) {
            return Optional.empty();
        }

        User user = found.get();
        if (!BCrypt.checkpw(plainTextPassword, user.getPasswordHash())) {
            return Optional.empty();
        }

        return Optional.of(user);
    }
}