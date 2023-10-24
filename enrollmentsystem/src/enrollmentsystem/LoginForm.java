package enrollmentsystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm {

    private JFrame loginFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private static Connection con = null;
   
    
    public LoginForm() {
        initialize();
    }

    private void initialize() {
        loginFrame = new JFrame();
        loginFrame.setTitle("Admin Login");
        loginFrame.setBounds(100, 100, 300, 200);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.getContentPane().setLayout(null);
       
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(30, 40, 80, 20);
        loginFrame.getContentPane().add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(120, 40, 150, 20);
        loginFrame.getContentPane().add(usernameField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(30, 80, 80, 20);
        loginFrame.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 80, 150, 20);
        loginFrame.getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 120, 80, 25);
        loginFrame.getContentPane().add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String username = usernameField.getText();
               String password = new String (passwordField.getPassword());
               
               try {
               	//Step 1 : Register the SQLite JDBC Driver
                   Class.forName("org.sqlite.JDBC");
                //Step 2 : connecting to our database
                     con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Ralp\\Documents\\studinfo.db"); // connecting to our 
                     con.setAutoCommit(false);
               
                String query = "SELECT * FROM admin Where user = ? and pass = ?";
                try (PreparedStatement preparedstatement = con.prepareStatement(query)) {
                      preparedstatement.setString(1, username);
                      preparedstatement.setString(2,password);
                      ResultSet resultSet = preparedstatement.executeQuery();
                      
                if (resultSet.next()) {
                      JOptionPane.showMessageDialog(null, "Login Succesfully");
                
                } else {
                	JOptionPane.showMessageDialog(null, "Incorrect Credentials","ERROR",JOptionPane.ERROR_MESSAGE);
                }     
                }
               } catch (Exception r) {
           		r.printStackTrace();
               }
            }
        });
    }

    public void setVisible(boolean visible) {
        loginFrame.setVisible(visible);
    }
}
