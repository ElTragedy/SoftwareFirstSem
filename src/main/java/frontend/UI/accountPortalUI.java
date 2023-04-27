package frontend.UI;

/*
 * This code uses a lot of functions like "Create User" and "create reservation"
 * which will need to commuicate with the UIBlackBox. This is a TODO: connect
 * this to the UIBlackBox.
 */

import frontend.UIBlackBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class accountPortalUI extends JFrame implements ActionListener {
    // Main Container
    private Container container;

    // Labels to identify the fields
    private JLabel greetingLabel;

    // Button for confirmation
    private JButton signOutButton;
    private JButton accountButton;
    private JButton newReservationButton;
    private JButton modifyReservationButton;

    public accountPortalUI() {
        UIBlackBox.saveAll();
        // Set All Components
        greetingLabel = new JLabel("Hello, " + UIBlackBox.getCurrentAccount().getFirstName()); // TODO: Add Hello, "user's name"
        greetingLabel.setFont(new Font("Barlow", Font.BOLD, 30));

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

        // Button for modifying reservation
        modifyReservationButton = new JButton("Modify Reservation");
        modifyReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // createAccountGUI createAccountGUI = new createAccountGUI();
                // createAccountGUI.createAndShowGui();
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
        greetingLabel.setBounds(50, 10, 250, 30);
        signOutButton.setBounds(300, 10, 100, 30);
        // promptLabel.setBounds(100, 60, 350, 30);
        newReservationButton.setBounds(50, 110, 200, 30);
        modifyReservationButton.setBounds(250, 110, 200, 30);
        //createRoomButton.setBounds(50, 160, 200, 30);
    }

    public void addComponents() {
        container.add(greetingLabel);
        container.add(signOutButton);
        container.add(newReservationButton);
        container.add(modifyReservationButton);
    }

    public void createAndShowGui() {
        accountPortalUI frame = new accountPortalUI();
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
