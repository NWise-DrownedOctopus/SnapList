package view;

import javax.swing.*;
import java.awt.*;
import controller.RegisterController;
import service.UserService;

public class RegisterView extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnRegister;
    private JButton btnLogin;
    private JLabel lblStatus;

    public RegisterView(UserService userService) {
        setTitle("Snap List — Register");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buildUI();
        new RegisterController(this, userService);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void buildUI() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        formPanel.add(new JLabel("Username"));
        txtUsername = new JTextField(20);
        formPanel.add(txtUsername);

        formPanel.add(new JLabel("Password"));
        txtPassword = new JPasswordField(20);
        formPanel.add(txtPassword);

        formPanel.add(new JLabel("Confirm Password"));
        txtConfirmPassword = new JPasswordField(20);
        formPanel.add(txtConfirmPassword);

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        lblStatus = new JLabel("Create a new account");
        btnRegister = new JButton("Register");
        btnLogin = new JButton("Back to Login");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnPanel.add(btnLogin);
        btnPanel.add(btnRegister);

        bottomPanel.add(lblStatus, BorderLayout.WEST);
        bottomPanel.add(btnPanel, BorderLayout.EAST);

        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Getters for controller
    public String getUsername() { return txtUsername.getText().trim(); }
    public String getPassword() { return new String(txtPassword.getPassword()); }
    public String getConfirmPassword() { return new String(txtConfirmPassword.getPassword()); }
    public JButton getBtnRegister() { return btnRegister; }
    public JButton getBtnLogin() { return btnLogin; }
    public void setStatus(String message) { lblStatus.setText(message); }
}