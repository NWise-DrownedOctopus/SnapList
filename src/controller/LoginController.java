package controller;

import model.CurrentUser;
import model.User;
import service.UserService;
import view.LoginView;
import view.RegisterView;
import view.CardListView;
import dao.CardDaoImplementation;
import service.CardService;

import javax.swing.JOptionPane;
import java.util.Optional;

public class LoginController {

    private final LoginView view;
    private final UserService userService;

    public LoginController(LoginView view, UserService userService) {
        this.view = view;
        this.userService = userService;
        initController();
    }

    private void initController() {
        view.getBtnLogin().addActionListener(e -> onLogin());
        view.getBtnRegister().addActionListener(e -> onRegister());
    }

    private void onLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            view.setStatus("Please enter both username and password");
            return;
        }

        Optional<User> result = userService.login(username, password);

        if (result.isEmpty()) {
            view.setStatus("Invalid username or password");
            return;
        }

        // Set the current user session
        CurrentUser.set(result.get());

        // Launch the main app window
        CardDaoImplementation cardDao = new CardDaoImplementation();
        CardService cardService = new CardService(cardDao);
        CardListController controller = new CardListController(cardService, userService);
        CardListView cardListView = new CardListView(controller);
        controller.setView(cardListView);

        cardListView.setVisible(true);
        view.dispose();
    }

    private void onRegister() {
        RegisterView registerView = new RegisterView(userService);
        registerView.setVisible(true);
        view.dispose();
    }
}