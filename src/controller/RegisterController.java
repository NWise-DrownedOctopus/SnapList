package controller;

import javax.swing.JOptionPane;

import model.CurrentUser;
import service.UserService;
// import view.LoginView;
import view.RegisterView;

public class RegisterController {

    private final RegisterView view;
    private final UserService userService;

    public RegisterController(RegisterView view, UserService userService) {
        this.view = view;
        this.userService = userService;
        initController();
    }

    private void initController() {
        view.getBtnRegister().addActionListener(e -> onRegister());
        view.getBtnLogin().addActionListener(e -> onBackToLogin());
    }

    private void onRegister() {
        String username = view.getUsername();
        String password = view.getPassword();
        String confirm = view.getConfirmPassword();

        // Validate inputs
        if (username.isEmpty()) {
            view.setStatus("Username cannot be empty");
            return;
        }
        if (password.length() < 6) {
            view.setStatus("Password must be at least 6 characters");
            return;
        }
        if (!password.equals(confirm)) {
            view.setStatus("Passwords do not match");
            return;
        }

        try {
            userService.registerUser(username, password);
            JOptionPane.showMessageDialog(view, "Account created! Please log in.");
            onBackToLogin();
        } catch (RuntimeException ex) {
            // Catches "Username already taken" from the DAO
            view.setStatus(ex.getMessage());
        }
    }

    private void onBackToLogin() {
        // LoginView loginView = new LoginView(userService);
        // loginView.setVisible(true);
        view.dispose();
    }
}