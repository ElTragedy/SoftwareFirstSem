package frontend.UI;

/*
 * This code uses a lot of functions like "Create User" and "create reservation"
 * which will need to communicate with the UIBlackBox. This is a TODO: connect
 * this to the UIBlackBox.
 */

import frontend.UIBlackBox;
import frontend.table.ReservationStatusTable;

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

    ReservationStatusTable reservationStatusTable;


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
                    loginUI loginUI = new loginUI();
                    loginUI.createAndShowGui();
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

        // Initialize Table
        reservationStatusTable = new ReservationStatusTable();


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
        newReservationButton.setBounds(50, 60, 200, 30);
        reservationStatusTable.setBounds(50, 90, 500, 200);
    }

    public void addComponents() {
        container.add(greetingLabel);
        container.add(signOutButton);
        container.add(newReservationButton);
        container.add(reservationStatusTable);
    }

    public void createAndShowGui() {
        accountPortalUI frame = new accountPortalUI();
        frame.setTitle("Account Portal");
        frame.setVisible(true);
        frame.setBounds(500, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
