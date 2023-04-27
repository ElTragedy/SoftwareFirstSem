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

        // Button for modifying reservation
        modifyReservationButton = new JButton("Modify Reservation");
        modifyReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
    }

    public void addComponents() {
        container.add(adminMessage);
        container.add(signOutButton);
        container.add(newReservationButton);
        container.add(modifyReservationButton);
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
