package controller;

// import javax.swing.JOptionPane;

import view.LoginView;
import view.RegisterView;
import view.TaskListView;

public class LoginController {

    LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    public void onLoginButtonClick() {
        // JOptionPane.showMessageDialog(loginView, "Login successful");
        TaskListView taskListView = new TaskListView();
        taskListView.setVisible(true);

        loginView.dispose();
    }

    public void onRegisterButtonClick() {

        RegisterView registerView = new RegisterView();
        registerView.setVisible(true);

        loginView.dispose();
    }
}