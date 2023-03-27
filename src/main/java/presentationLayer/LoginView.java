package presentationLayer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginView extends JFrame {

    Container container = getContentPane();

    private JLabel usernameLabel = new JLabel("Username:");
    private JTextField username = new JTextField(5);
    private JLabel passwordLabel = new JLabel("Password:");
    private JPasswordField password = new JPasswordField(5);
    private JButton login = new JButton("Login");
    private JButton register = new JButton("Register");

    public LoginView() {

        //adjust size and set layout
        setPreferredSize(new Dimension(500, 300));
        setLayoutManag();
        setLocAndSize();
        addComponent();

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void setLayoutManag() {
        container.setLayout(null);
    }

    public void setLocAndSize() {

        username.setBounds(170, 20, 150, 30);
        usernameLabel.setBounds(80, 20, 100, 30);

        password.setBounds(170, 60, 150, 30);
        passwordLabel.setBounds(80, 60, 150, 30);

        login.setBounds(70, 150, 150, 30);
        register.setBounds(270, 150, 150, 30);

    }

    public void addComponent() {
        add(usernameLabel);
        add(username);
        add(passwordLabel);
        add(password);
        add(login);
        add(register);
    }

    public void addLoginListener(ActionListener a) {
        login.addActionListener(a);
    }

    public void addRegisterListener(ActionListener a) {
        register.addActionListener(a);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String getUsername() {
        return username.getText();
    }

    public char[] getPassword() {
        return password.getPassword();
    }


}

