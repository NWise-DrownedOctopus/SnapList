package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.RegisterController;

public class RegisterView extends JFrame {

    RegisterController controller;
    
    JTextField txtFullname, txtUsername, txtEmail;
    JPasswordField txtPassword, txtConfirmPassword;
    JButton btnLogin, btnRegister;
    JLabel lblStatus;

    public RegisterView() {

        controller = new RegisterController(this);

        setTitle("Register");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buildUI();
        pack();
    }

    private void buildUI() {

        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        formPanel.add(new JLabel("Full name"));
        txtFullname = new JTextField(20);
        formPanel.add(txtFullname);

        formPanel.add(new JLabel("Username"));
        txtUsername = new JTextField(20);
        formPanel.add(txtUsername);

        formPanel.add(new JLabel("Email"));
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Password"));
        txtPassword = new JPasswordField(20);
        formPanel.add(txtPassword);

        formPanel.add(new JLabel("Confirm Password"));
        txtConfirmPassword = new JPasswordField(20);
        formPanel.add(txtConfirmPassword);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        lblStatus = new JLabel("Enter username and password or register a new account");

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> controller.onLoginButtonClick());

        btnRegister = new JButton("Register");
        btnRegister.addActionListener(e -> controller.onRegisterButtonClick());

        bottomPanel.add(lblStatus, BorderLayout.WEST);
        bottomPanel.add(btnLogin, BorderLayout.CENTER);
        bottomPanel.add(btnRegister, BorderLayout.EAST);

        add(formPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);
    }
}