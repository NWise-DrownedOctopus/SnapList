// package view;

// import java.awt.BorderLayout;

// import javax.swing.BorderFactory;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import java.awt.GridLayout;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JPasswordField;
// import javax.swing.JTextField;

// import controller.LoginController;

// public class LoginView extends JFrame {

//     JTextField txtUsername;
//     JPasswordField txtPassword;
//     JButton btnLogin, btnRegister;
//     JLabel lblStatus;

//     LoginController controller;

//     public LoginView() {

//         controller = new LoginController(this);
//         buildUI();
//         setTitle("Login");
//         setDefaultCloseOperation(EXIT_ON_CLOSE);        
//         pack();
//         setLocationRelativeTo(null);
//     }

//     private void buildUI() {
//         setLayout(new BorderLayout());
//         JPanel formPanel = new JPanel(new GridLayout(2, 2, 8, 8));
//         formPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

//         formPanel.add(new JLabel("Username"));
//         txtUsername = new JTextField(20);
//         formPanel.add(txtUsername);

//         formPanel.add(new JLabel("Password"));
//         txtPassword = new JPasswordField(20);
//         formPanel.add(txtPassword);

//         // bottom components
//         JPanel bottomPanel = new JPanel(new BorderLayout());
//         lblStatus = new JLabel("Enter username and password or register a new account");

//         btnLogin = new JButton("Login");
//         btnLogin.addActionListener(e -> controller.onLoginButtonClick());

//         btnRegister = new JButton("Register");
//         btnRegister.addActionListener(e -> controller.onRegisterButtonClick());

//         bottomPanel.add(lblStatus,BorderLayout.WEST);
//         bottomPanel.add(btnLogin, BorderLayout.CENTER);
//         bottomPanel.add(btnRegister, BorderLayout.EAST);

//         add(formPanel, BorderLayout.NORTH);
//         add(bottomPanel, BorderLayout.CENTER);
//     }
// }