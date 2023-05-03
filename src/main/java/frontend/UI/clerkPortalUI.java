package frontend.UI;

import frontend.UIBlackBox;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class clerkPortalUI extends JFrame implements ActionListener {
    // Main Container
    private Container container;

    // Labels for identifying areas on screen
    private JLabel clerkLabel;

    // Buttons for sign out/account
    private JButton signOutButton;
    private JButton accountButton;

    // Clerk use-case field labels
    private JLabel guestServicesLabel, roomServicesLabel;

    // Clerk use-case buttons
    private JButton newReservationButton;
    private JButton modifyReservationButton;
    private JButton addRoomButton;
    private JButton modifyRoom;
    private JButton cancelGuestReservation;
    private JButton availableRooms;

    public clerkPortalUI() {
        // Set All Components
        clerkLabel = new JLabel("Clerk Portal");
        clerkLabel.setFont(new Font("Barlow", Font.BOLD, 30));

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

        // Add image to account button
        ImageIcon imageIcon = new ImageIcon("src/main/resources/Frontend_Resources/accountImage.png");
        Image image = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(image);
        // Modify profile button
        accountButton = new JButton(imageIcon);
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProfileUI editProfileUI = new editProfileUI();
                editProfileUI.createAndShowGui();
                dispose();
            }
        });

        // Guest services
        guestServicesLabel = new JLabel("Guest Services");
        guestServicesLabel.setFont( new Font("Barlow", Font.BOLD, 15) );


        // Room services
        roomServicesLabel = new JLabel("Room Services");
        roomServicesLabel.setFont( new Font("Barlow", Font.BOLD, 15) );


        // Button for creating new reservation
        newReservationButton = new JButton("New Reservation");
        newReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField u = new JTextField(10);
                JPasswordField p = new JPasswordField(10);

                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("username:"));
                myPanel.add(u);
                myPanel.add(new JLabel("password"));
                myPanel.add(p);

                int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Email and Password", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    if (u.getText() == "" || p.getText() == "") {
                        JOptionPane.showMessageDialog(null, "Please Enter Both fields", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String email = u.getText();
                        char[] password = p.getText().toCharArray();
                        if (UIBlackBox.accountExists(email, password)) {
                            //newReservationUI newReservationUI = new newReservationUI();
                            //newReservationUI.createAndShowGui(email, password);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Either email or password is Incorrect");
                        }
                    }
                }
            }
        });

        // Button for modifying reservation
        //TODO: modify reservation hasn't been implemented yet
        modifyReservationButton = new JButton("Modify Reservation");
        modifyReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                newReservationUI newReservationUI = new newReservationUI();
//                newReservationUI.createAndShowGui();
                dispose();
            }
        });




        addRoomButton = new JButton("Add Room");
        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addRoomUI();
                //addroomui.createAndShowGui();
                //was creating two instances
                dispose();
            }
        });

        modifyRoom = new JButton("Modify Room");
        modifyRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomNum = JOptionPane.showInputDialog("Enter Room Number:");
                int i = -1;
                if (roomNum != null) {
                    i = Integer.parseInt(roomNum);

                    if (UIBlackBox.roomExists(i)) {
                        new modifyRoomUI(roomNum);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Room Number Does Not Exist");
                    }
                }
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
        clerkLabel.setBounds(50, 10, 250, 30);
        signOutButton.setBounds(300, 10, 100, 30);
        accountButton.setBounds(400, 10, 30, 30);

        // Guest services
        guestServicesLabel.setBounds(50, 80, 150, 30);
        newReservationButton.setBounds(50, 110, 150, 30);
        modifyReservationButton.setBounds(50, 150, 150, 30);

        // Room services
        roomServicesLabel.setBounds(250, 80, 150, 30);
        availableRooms.setBounds(250, 110, 150, 30);


        cancelGuestReservation.setBounds(50, 200, 200, 30);
        addRoomButton.setBounds(275, 150, 200, 30);
        modifyRoom.setBounds(275, 200, 200, 30);
    }

    public void addComponents() {
        container.add(clerkLabel);
        container.add(signOutButton);
        container.add(accountButton);

        // Guest services
        container.add(guestServicesLabel);
        container.add(newReservationButton);
        container.add(modifyReservationButton);

        // Room services
        container.add(roomServicesLabel);
        container.add(availableRooms);
        //container.add(cancelGuestReservation);

        container.add(addRoomButton);
        container.add(modifyRoom);
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
