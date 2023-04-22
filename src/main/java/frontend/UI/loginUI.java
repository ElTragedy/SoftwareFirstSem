package frontend.UI;

/*
 * No need for black box connections, this is log in GUI thing
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;
import backend.*;


public class loginUI extends JFrame implements ActionListener{
    private Container container;
    private JLabel message;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;

    public loginUI() {
        // Set header for window
        message = new JLabel("Welcome To <HOTEL NAME>");
        message.setFont(new Font("Barlow", Font.BOLD, 30));

        // Add contact/login info labels and fields
        emailLabel = new JLabel("Email");
        emailField = new JTextField();

        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();

        // Add login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Arrays.toString(passwordField.getPassword()));
                if (Objects.equals(emailField.getText(), "") && Objects.equals(Arrays.toString(passwordField.getPassword()), "[]")) {
                    // MAKE FUNCTION TO ITERATE THROUGH ALL FIELDS
                    JOptionPane.showMessageDialog(container, "Please fill out both fields!",
                            "Invalid Email or Password", JOptionPane.ERROR_MESSAGE);
                } else if (Objects.equals(emailField.getText(), "admin") && Objects.equals(Arrays.toString(passwordField.getPassword()), "[]")) {
                    adminPortalUI adminPortalUI = new adminPortalUI();
                    adminPortalUI.createAndShowGui();
                    dispose();
                }
                String email = emailField.getText();
                char[] password = passwordField.getPassword();
                if(email.isEmpty() || password.length == 0){
                     JOptionPane.showMessageDialog(container, "Please fill out both fields!",
                    "Invalid Email or Password", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Account a = UIBlackBox.getAccount(email, password);
                    if(a == null){
                        JOptionPane.showMessageDialog(container, "Invalid Email or Password",
                        "Invalid Email or Password", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        UIBlackBox.setCurrentAccount(a);
                        accountPortalGUI accountPortalGUI = new accountPortalGUI();
                        accountPortalGUI.createAndShowGui();
                        dispose();
                        
                    }
                }
            }
        });

        // Add Create Account Button
        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccountUI createAccountUI = new createAccountUI();
                createAccountUI.createAndShowGui();
                dispose();
            }
        });

        // Add and set container
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
    }

    // Sets all labels/fields bounds
    public void setBounds() {
        message.setBounds(20, 20, 460, 30);
        message.setHorizontalAlignment(JLabel.CENTER);

        emailLabel.setBounds(50, 100, 100, 30);
        //message.setHorizontalAlignment(JLabel.CENTER);

        emailField.setBounds(150, 100, 200, 30);
        //emailField.setHorizontalAlignment(JLabel.CENTER);

        passwordLabel.setBounds(50, 150, 100, 30);
        passwordField.setBounds(150, 150, 200, 30);

        loginButton.setBounds(150, 200, 200, 30);
        loginButton.setHorizontalAlignment(JLabel.CENTER);

        createAccountButton.setBounds(150, 250, 200, 30);
        createAccountButton.setHorizontalAlignment(JLabel.CENTER);
    }

    // Adds labels and fields to container
    public void addComponents() {
        container.add(message);

        container.add(emailLabel);
        container.add(emailField);

        container.add(passwordLabel);
        container.add(passwordField);

        container.add(loginButton);
        container.add(createAccountButton);
    }

    public static void createAndShowGui() {
        loginUI frame = new loginUI();
        frame.setTitle("Login");
        frame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.pack();
        frame.setBounds(500, 100, 500, 500);
        //frame.setSize(screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
//        loginUI frame = new loginUI();
//        frame.setTitle("Landing Page");
//        frame.setVisible(true);
//        frame.setBounds(500, 100, 500, 500);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(true);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}

