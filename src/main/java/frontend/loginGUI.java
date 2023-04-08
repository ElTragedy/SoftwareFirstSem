package frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;


public class loginGUI extends JFrame implements ActionListener{
    private JButton backButton;
    private Container container;
    private JLabel message;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;

    public loginGUI() {
        // Implement Back Button
        backButton = new JButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeGUI welcomeGUI = new welcomeGUI();
                welcomeGUI.createAndShowGui();
                dispose();
            }
        });
        ImageIcon imageIcon = new ImageIcon("C:/Users/carma/Desktop/left.png");
        Image image = imageIcon.getImage().getScaledInstance(30, 30,  Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        backButton.setIcon(imageIcon);

        // Set header for window
        message = new JLabel("Welcome back, please enter your information to login.");
        message.setFont(new Font("Courier", Font.BOLD, 20));

        // Add contact/login info labels and fields
        emailLabel = new JLabel("Email");
        emailField = new JTextField();

        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();

        // Add register button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Arrays.toString(passwordField.getPassword()));
                if (Objects.equals(emailField.getText(), "") && Objects.equals(Arrays.toString(passwordField.getPassword()), "[]")) {
                    // MAKE FUNCTION TO ITERATE THROUGH ALL FIELDS
                    JOptionPane.showMessageDialog(container, "Please fill out both fields!",
                            "Invalid Email or Password", JOptionPane.ERROR_MESSAGE);
                }
                //String email = emailField.getText();
                //String password = Arrays.toString(passwordField.getPassword());
                //System.out.println(email + " " + password);
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
        backButton.setBounds(0, 0, 30, 30);

        message.setBounds(50, 10, 600, 30);
        emailLabel.setBounds(50, 60, 100, 30);
        emailField.setBounds(130, 60, 200, 30);

        passwordLabel.setBounds(50, 110, 100, 30);
        passwordField.setBounds(130, 110, 200, 30);

        loginButton.setBounds(130, 160, 200, 30);
    }

    // Adds labels and fields to container
    public void addComponents() {
        container.add(backButton);

        container.add(message);

        container.add(emailLabel);
        container.add(emailField);

        container.add(passwordLabel);
        container.add(passwordField);

        container.add(loginButton);
    }

    public void createAndShowGui() {
        loginGUI frame = new loginGUI();
        frame.setTitle("Login");
        frame.setVisible(true);
        frame.setBounds(500, 100, 800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

