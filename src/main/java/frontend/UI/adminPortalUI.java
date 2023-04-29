package frontend.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminPortalUI extends JFrame implements ActionListener {
    // Main Container
    private Container container;

    // Labels to identify the fields
    private JLabel adminMessage;

    // Button for confirmation
    private JButton signOutButton;
    private JButton accountButton;
    private JButton newReservationButton;
    private JButton modifyReservationButton;
    private JButton createClerkButton;

    // button for reset user account passwords
    private JButton adminResetUserPassword;

    public adminPortalUI() {
        // Set All Components
        adminMessage = new JLabel("Admin Portal"); // TODO: Add Hello, "user's name"
        adminMessage.setFont(new Font("Barlow", Font.BOLD, 30));

        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit the program?", "Exit Program Message Box",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        // Button for creating new reservation
        newReservationButton = new JButton("New Reservation");
        newReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newReservationUI newReservationUI = new newReservationUI();
                newReservationUI.createAndShowGui();
                dispose();
            }
        });

        // button for admin reset user passwords
        adminResetUserPassword = new JButton("Reset User Passwords");
        adminResetUserPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminResetUserPasswordUI adminResetUserPasswordUI = new adminResetUserPasswordUI();
                adminResetUserPasswordUI.createAndShowGui();
                dispose();
            }
        });

        // Button for modifying reservation
        modifyReservationButton = new JButton("Modify Reservation");
        modifyReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Button vor creating new clerk
        createClerkButton = new JButton("Create Clerk");
        createClerkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createClerkUI createClerkUI = new createClerkUI();
                createClerkUI.createAndShowGui();
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
        adminMessage.setBounds(50, 10, 250, 30);
        signOutButton.setBounds(300, 10, 100, 30);
        // promptLabel.setBounds(100, 60, 350, 30);
        newReservationButton.setBounds(50, 110, 200, 30);
        modifyReservationButton.setBounds(250, 110, 200, 30);
        createClerkButton.setBounds(150, 160, 200, 30);
        adminResetUserPassword.setBounds(150, 210, 200, 30);
    }

    public void addComponents() {
        container.add(adminMessage);
        container.add(signOutButton);
        container.add(newReservationButton);
        container.add(modifyReservationButton);
        container.add(createClerkButton);
        container.add(adminResetUserPassword);
    }

    public void createAndShowGui() {
        adminPortalUI frame = new adminPortalUI();
        frame.setTitle("Account Portal");
        frame.setVisible(true);
        frame.setBounds(500, 100, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
