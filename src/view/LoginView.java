package view;

import javax.swing.*;
import java.awt.*;
import controller.LoginController;
import service.UserService;

public class LoginView extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JLabel lblStatus;

    public LoginView(UserService userService) {
        setTitle("Snap List — Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buildUI();
        new LoginController(this, userService);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void buildUI() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        formPanel.add(new JLabel("Username"));
        txtUsername = new JTextField(20);
        formPanel.add(txtUsername);

        formPanel.add(new JLabel("Password"));
        txtPassword = new JPasswordField(20);
        formPanel.add(txtPassword);

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        lblStatus = new JLabel("Enter your credentials to continue");
        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnPanel.add(btnRegister);
        btnPanel.add(btnLogin);

        bottomPanel.add(lblStatus, BorderLayout.WEST);
        bottomPanel.add(btnPanel, BorderLayout.EAST);

        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Getters for controller
    public String getUsername() { return txtUsername.getText().trim(); }
    public String getPassword() { return new String(txtPassword.getPassword()); }
    public JButton getBtnLogin() { return btnLogin; }
    public JButton getBtnRegister() { return btnRegister; }
    public void setStatus(String message) { lblStatus.setText(message); }
}