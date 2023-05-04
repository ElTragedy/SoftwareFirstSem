package frontend.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;
import backend.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import frontend.UIBlackBox;


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
        UIBlackBox.loadAll();

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
                String email = emailField.getText();
                char[] password = passwordField.getPassword();

                if(email.isEmpty() || password.length == 0) {
                    JOptionPane.showMessageDialog(container, "Please fill out both fields!",
                            "Invalid Email or Password", JOptionPane.ERROR_MESSAGE);
                } else {
                    Account a = UIBlackBox.getAccount(email, password);
                    if(a == null) {
                        JOptionPane.showMessageDialog(container, "Invalid Email or Password",
                                "Invalid Email or Password", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if(a.getAccess().equals("admin")) {
                            UIBlackBox.setCurrentAccount(a);
                            adminPortalUI adminPortalUI = new adminPortalUI();
                            adminPortalUI.createAndShowGui();
                            dispose();
                        } else if(a.getAccess().equals("clerk")) {
                            UIBlackBox.setCurrentAccount(a);
                            clerkPortalUI clerkPortalUI = new clerkPortalUI();
                            clerkPortalUI.createAndShowGui();
                            dispose();
                        } else {
                            UIBlackBox.setCurrentAccount(a);
                            accountPortalUI accountPortalGUI = new accountPortalUI();
                            accountPortalGUI.createAndShowGui();
                            dispose();
                        }
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
        Integer width = getHeight();
        Integer height = container.getHeight();

        message.setBounds(0, 0, 460, 30);
        message.setHorizontalAlignment(SwingConstants.CENTER);

        emailLabel.setBounds(50, 100, 100, 30);
        emailField.setBounds(150, 100, 200, 30);

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
        // Set Look and Feel of UI to FlatDarcula
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }

        loginUI frame = new loginUI();
        frame.setTitle("Login");
        frame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Integer newWidth = screenSize.width/2;
        Integer newHeight = screenSize.height/2;
        //frame.pack();

        frame.setBounds(500, 100, 500, 500);
        frame.setLocationRelativeTo(null);
        //frame.setSize(screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}

