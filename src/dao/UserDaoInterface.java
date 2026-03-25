package dao;
import java.util.Optional;
import model.*;

public interface UserDaoInterface {
    long addUser(User user);
    //Optional<User> findById(long id);
    //Optional<User> findByUserName(String name);
    //long updatePassword(String username);
}
