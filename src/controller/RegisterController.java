package controller;

import javax.swing.JOptionPane;

import view.LoginView;
import view.RegisterView;

public class RegisterController {
    
    RegisterView registerView;

    public RegisterController(RegisterView registerView) {
        this.registerView = registerView;
    }

    public void onRegisterButtonClick() {
        JOptionPane.showMessageDialog(registerView, "Registration Sucessful");
    }

    public void onLoginButtonClick() {
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
        registerView.dispose();
    }
}
