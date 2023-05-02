package frontend.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import backend.Account;
import java.util.ArrayList;

import frontend.UIBlackBox;

public class adminResetUserPasswordUI extends JFrame {
    private JButton backButton;
    private JLabel message;
    private JLabel userNameLabel;
    private JTextField userNameField;
    private JLabel newPasswordLabel;
    private JTextField newPasswordField;
    private JButton resetPasswordButton;
    private Container container;

    public adminResetUserPasswordUI() {
        // Implement Back Button
        backButton = new JButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPortalUI adminPortalUI = new adminPortalUI();
                adminPortalUI.createAndShowGui();
                dispose();
            }
        });

        ImageIcon imageIcon = new ImageIcon("src/main/resources/Frontend_Resources/backButton.png");
        Image image = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        backButton.setIcon(imageIcon);

        // Set header for window
        message = new JLabel("Reset User Password");
        message.setFont(new Font("Barlow", Font.BOLD, 20));

        // Add user ID labels and fields
        userNameLabel = new JLabel("User name");
        userNameField = new JTextField();

        // Add password to reset to
        newPasswordLabel = new JLabel("New Password");
        newPasswordField = new JTextField();

        // Add reset password button
        resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                ArrayList<Account> accounts = UIBlackBox.getAllAccounts();
                if(accounts == null) {
                    JOptionPane.showMessageDialog(null, "Password reset failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for(Account account : accounts) {
                    if(account.getEmail().equals(userName)) {
                        if(UIBlackBox.resetPassword(account, newPasswordField.getText())) {
                            JOptionPane.showMessageDialog(null, "Password reset successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            UIBlackBox.saveAll();
                        } else {
                            JOptionPane.showMessageDialog(null, "Password reset failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        return;
                    }
                }
                
            }
        });

        // Add and set container
        container = new JPanel();
        container.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scrollPane);

        setBounds();
        container.setPreferredSize(new Dimension(500, 200));
        addComponents();
    }

    // Sets all labels/fields bounds
    public void setBounds() {
        backButton.setBounds(0, 0, 30, 30);
        message.setBounds(50, 10, 600, 30);
        userNameLabel.setBounds(50, 60, 100, 30);
        userNameField.setBounds(170, 60, 200, 30);
        newPasswordLabel.setBounds(50, 100, 100, 30);
        newPasswordField.setBounds(170, 100, 200, 30);
        resetPasswordButton.setBounds(170, 160, 200, 30);
    }

    // Adds labels and fields to container
    public void addComponents() {
        container.add(backButton);
        container.add(message);
        container.add(userNameLabel);
        container.add(userNameField);
        container.add(newPasswordLabel);
        container.add(newPasswordField);
        container.add(resetPasswordButton);
    }

    public void createAndShowGui() {
        adminResetUserPasswordUI frame = new adminResetUserPasswordUI();
        frame.setTitle("Admin - Reset User Password");
        frame.setVisible(true);
        frame.setBounds(500, 15, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }
}

