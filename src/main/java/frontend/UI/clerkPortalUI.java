package frontend.UI;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class clerkPortalUI extends JFrame implements ActionListener {
    // Main Container
    private Container container;

    // Labels to identify the fields
    private JLabel clerkMessage;

    // Button for confirmation
    private JButton signOutButton;
    private JButton newReservationButton;
    private JButton modifyReservationButton;
    private JButton modifyProfile;
    private JButton addRoom;
    private JButton modifyRoom;
    private JButton cancelGuestReservation;
    private JButton availableRooms;

    public clerkPortalUI() {
        // Set All Components
        clerkMessage = new JLabel("Clerk Portal"); // TODO: Add Hello, "user's name"
        clerkMessage.setFont(new Font("Barlow", Font.BOLD, 30));

        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to sign out?", "Sign Out Message Box",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    loginUI login = new loginUI();
                    login.createAndShowGui();
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
        //TODO: modify reservation hasn't been implemented yet
        modifyReservationButton = new JButton("Modify Reservation");
        modifyReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        modifyProfile = new JButton("Modfiy Profile");
        modifyProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProfileUI editProfileUI = new editProfileUI();
                editProfileUI.createAndShowGui();
                dispose();
            }
        });

        addRoom = new JButton("Add Room");
        addRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoomUI addroomui = new addRoomUI();
                addroomui.createAndShowGui();
                dispose();
            }
        });

        modifyRoom = new JButton("Modify Room");
        modifyRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Option 1: maybe prefill addRoom with existing info
                //TODO Option 2: maybe create new portal and have it delete the given room number or have a criteria to pick a room
            }
        });

        cancelGuestReservation = new JButton("Cancel Guest Reservation");
        cancelGuestReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO maybe make a text box with user email then destroy the reservation(s) tied to it
            }
        });

        availableRooms = new JButton("Available Rooms");
        modifyReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO maybe make criteria they are looking for then have a table load with the following info
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
        clerkMessage.setBounds(50, 10, 250, 30);
        signOutButton.setBounds(300, 10, 100, 30);
        availableRooms.setBounds(50, 50, 200, 30);
        newReservationButton.setBounds(50, 100, 200, 30);
        modifyReservationButton.setBounds(50, 150, 200, 30);
        cancelGuestReservation.setBounds(50, 200, 200, 30);
        modifyProfile.setBounds(275, 100, 200, 30);
        addRoom.setBounds(275, 150, 200, 30);
        modifyRoom.setBounds(275, 150, 200, 30);
    }

    public void addComponents() {
        container.add(clerkMessage);
        container.add(signOutButton);
        container.add(newReservationButton);
        container.add(modifyReservationButton);
        container.add(cancelGuestReservation);
        container.add(modifyProfile);
        container.add(addRoom);
        container.add(modifyRoom);
        container.add(availableRooms);
    }

    public static void createAndShowGui() {
        // Set Look and Feel of UI to FlatDarcula
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }

        clerkPortalUI frame = new clerkPortalUI();
        frame.setTitle("Clerk Portal");
        frame.setVisible(true);
        frame.setBounds(500, 100, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
