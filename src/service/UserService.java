package service;

import dao.UserDaoImplementation;
import model.User;

public class UserService {

    private final UserDaoImplementation userDao;

    public UserService(UserDaoImplementation userDao) {
        this.userDao = userDao;
    }

    public User registerUser(String fullName) {
        User newUser = new User();
        newUser.setName(fullName);

        long id = userDao.addUser(newUser);
        newUser.setId(id);
        return newUser;
    }
}
