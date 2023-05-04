package frontend.UI;

import backend.Account;
import frontend.UIBlackBox;

import com.formdev.flatlaf.FlatDarculaLaf;
import frontend.table.NewReservationPopup;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

public class clerkPortalUI extends JFrame implements ActionListener {
    // Main Container
    private Container container;

    // Labels for identifying areas on screen
    private JLabel clerkLabel;

    // Buttons for sign out/account
    private JButton signOutButton;
    private JButton accountButton;

    // Table attributes
    private JLabel allRoomsLabel;
    private JTable roomsTable;
    private JPanel tablePanel;
    private JButton addRoomButton;
    private JButton modifyRoom;

    // Clerk attributes
    private JLabel guestServicesLabel;
    private JLabel searchUserLabel;
    private JTextField searchUserField;
    private JButton searchUserButton;
    private JButton newReservationButton;
    private JButton modifyReservationButton;
    private JButton cancelGuestReservation;


    /**
     * Creates the UI for the clerk portal
     */
    public clerkPortalUI() {
        // Initialize table
        tablePanel = new JPanel();

        // Declare Column Headers
        String[] columnHeader = {"Room Number", "Room Size", "Room Type","Start Date", "End Date"};
        String[][] data = {
                {"123", "Suite", "2023-05-01", "2023-05-07"},
                {"312", "Single King", "2023-04-28", "2023-05-07"},
                {"166", "Double King", "2023-05-01", "2023-05-07"},
                {"123", "Suite", "2023-05-01", "2023-05-07"},
                {"312", "Single King", "2023-04-28", "2023-05-07"},
                {"166", "Double King", "2023-05-01", "2023-05-07"},
                {"123", "Suite", "2023-05-01", "2023-05-07"},
                {"312", "Single King", "2023-04-28", "2023-05-07"},
                {"166", "Double King", "2023-05-01", "2023-05-07"},
                {"123", "Suite", "2023-05-01", "2023-05-07"},
                {"312", "Single King", "2023-04-28", "2023-05-07"},
                {"166", "Double King", "2023-05-01", "2023-05-07"},
                {"123", "Suite", "2023-05-01", "2023-05-07"},
                {"312", "Single King", "2023-04-28", "2023-05-07"},
                {"166", "Double King", "2023-05-01", "2023-05-07"},
                {"123", "Suite", "2023-05-01", "2023-05-07"},
                {"312", "Single King", "2023-04-28", "2023-05-07"},
                {"166", "Double King", "2023-05-01", "2023-05-07"}
        };

        // Configure Table Basics
        DefaultTableModel model = new DefaultTableModel(data, columnHeader) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        roomsTable = new JTable(model);
        roomsTable.setPreferredScrollableViewportSize(new Dimension(540, 290));
        roomsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        roomsTable.getTableHeader().setReorderingAllowed(false);
        roomsTable.setFillsViewportHeight(true);
        tablePanel.add(new JScrollPane(roomsTable));

        // TODO: ADD FUNCTION THAT GETS ALL ROOMS

        /*
        model.setRowCount(0);
        for(Vector<String> i : UIBlackBox){
            model.addRow(i);
        }
        */


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
        guestServicesLabel.setFont( new Font("Barlow", Font.BOLD, 30) );


        // Room services
        allRoomsLabel = new JLabel("All Rooms");
        allRoomsLabel.setFont( new Font("Barlow", Font.BOLD, 30) );

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

        // Room buttons
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


        // Guest Services
        searchUserLabel = new JLabel("Enter Guest Email");
        searchUserLabel.setFont(new Font("Barlow", Font.BOLD, 15));
        searchUserField = new JTextField();
        final Account[] guest = new Account[1];
        // Add image to search button
        ImageIcon searchIcon = new ImageIcon("src/main/resources/Frontend_Resources/searchIcon.png");
        Image search = searchIcon.getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING);
        searchIcon = new ImageIcon(search);

        searchUserButton = new JButton();
        searchUserButton.setIcon(searchIcon);
        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(searchUserField.getText(), "")) {
                    JOptionPane.showMessageDialog(null, "Please Enter Email", "Please Try Again", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (UIBlackBox.getAccount(searchUserField.getText()) != null) {
                        guest[0] =UIBlackBox.getAccount(searchUserField.getText());
                        System.out.println(guest[0].getEmail());
                        model.setRowCount(0);
                        for(Vector<String> i : UIBlackBox.getReservationsByEmail(UIBlackBox.getCurrentAccount().getEmail())){
                            model.addRow(i);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No Guest Found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        // Button for creating new reservation
        newReservationButton = new JButton("New Reservation");
        newReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(guest[0] == null) {
                    JOptionPane.showMessageDialog(null, "No Guest Selected!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            NewReservationPopup newReservationPopup = new NewReservationPopup(roomsTable, guest[0]);
                            newReservationPopup.createAndShowGui();
                        }
                    });
                }
            }});
//        newReservationButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JTextField u = new JTextField(10);
//                JPasswordField p = new JPasswordField(10);
//
//                JPanel myPanel = new JPanel();
//                myPanel.add(new JLabel("username:"));
//                myPanel.add(u);
//                myPanel.add(new JLabel("password"));
//                myPanel.add(p);
//                if (searchUserField.getText() == "") {
//                    int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Email", JOptionPane.OK_CANCEL_OPTION);
//                }
//
//                Account guest = UIBlackBox.getAccount(searchUserField.getText());
//                if (guest != null) {
//                    if (u.getText() == "") {
//                        JOptionPane.showMessageDialog(null, "Please Enter Both fields", "Error", JOptionPane.ERROR_MESSAGE);
//                    } else {
//                        String email = u.getText();
//                        char[] password = p.getText().toCharArray();
//                        if (UIBlackBox.accountExists(email, password)) {
////                            newReservationUI newReservationUI = new newReservationUI();
////                            newReservationUI.createAndShowGui(email, password);
//                            dispose();
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Either email or password is Incorrect");
//                        }
//                    }
//                }
//            }
//        });

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

        cancelGuestReservation = new JButton("Cancel Reservation");
        cancelGuestReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO maybe make a text box with user email then destroy the reservation(s) tied to it
            }
        });

        // Add and set container
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
    }

    /**
     * Sets bounds for all components
     */
    public void setBounds() {
        clerkLabel.setBounds(50, 10, 250, 30);
        signOutButton.setBounds(610, 10, 100, 30);
        accountButton.setBounds(710, 10, 30, 30);

        // Table
        allRoomsLabel.setBounds(50, 80, 150, 30);
        tablePanel.setBounds(50, 110, 550, 300);
        addRoomButton.setBounds(610, 115, 150, 30);
        modifyRoom.setBounds(610, 145, 150, 30);

        // Guest services
        guestServicesLabel.setBounds(50, 410, 250, 30);
        searchUserLabel.setBounds(50, 450, 150, 30);
        searchUserField.setBounds(200, 450, 270, 30);
        searchUserButton.setBounds(470, 450, 30, 30);
        newReservationButton.setBounds(50, 480, 150, 30);
        modifyReservationButton.setBounds(200, 480, 150, 30);
        cancelGuestReservation.setBounds(350, 480, 150, 30);
    }

    /**
     * Add all components to the container
     */
    public void addComponents() {
        container.add(clerkLabel);
        container.add(signOutButton);
        container.add(accountButton);

        // Rooms table
        container.add(allRoomsLabel);
        container.add(tablePanel);
        container.add(addRoomButton);
        container.add(modifyRoom);

        // Guest services
        container.add(guestServicesLabel);
        container.add(searchUserLabel);
        container.add(searchUserField);
        container.add(searchUserButton);
        container.add(newReservationButton);
        container.add(modifyReservationButton);
        container.add(cancelGuestReservation);
    }

    /**
     * Build UI and display it
     */
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
        frame.setBounds(500, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
