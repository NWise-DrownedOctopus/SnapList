package controller;

import view.LoginView;
import view.RegisterView;
import view.CardListView;

public class LoginController {

    LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    public void onLoginButtonClick() {
        CardListView taskListView = new CardListView();
        taskListView.setVisible(true);

        loginView.dispose();
    }

    public void onRegisterButtonClick() {

        RegisterView registerView = new RegisterView();
        registerView.setVisible(true);

        loginView.dispose();
    }
}