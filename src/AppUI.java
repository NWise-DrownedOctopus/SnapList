import dao.UserDaoImplementation;
import service.UserService;
import view.LoginView;

public class AppUI {
    public static void main(String[] args) {
        UserDaoImplementation userDao = new UserDaoImplementation();
        UserService userService = new UserService(userDao);

        LoginView loginView = new LoginView(userService);
        loginView.setVisible(true);
    }
}