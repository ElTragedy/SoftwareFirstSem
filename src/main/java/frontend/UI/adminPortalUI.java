package frontend.UI;

import com.formdev.flatlaf.FlatDarculaLaf;

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

    /**
     * Creates a graphical user interface for the admin portal with various buttons to manage the system.
     * @param none
     * @return none
     */
    public adminPortalUI() {
        // Set All Components
        adminMessage = new JLabel("Admin Portal"); // TODO: Add Hello, "user's name"
        adminMessage.setFont(new Font("Barlow", Font.BOLD, 30));

        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to sign out?", "Sign Out Message Box",
                        JOptionPane.YES_NO_OPTION);

                // If user selects yes, go to login page
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
                newReservationUI newReservationUI = new newReservationUI(adminPortalUI.this);
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

    /**
     * Sets the bounds for all labels and fields in the admin portal UI.
     * @param none
     * @return none
     */
    public void setBounds() {
        adminMessage.setBounds(50, 10, 250, 30);
        signOutButton.setBounds(300, 10, 100, 30);
        // promptLabel.setBounds(100, 60, 350, 30);
        newReservationButton.setBounds(50, 110, 200, 30);
        modifyReservationButton.setBounds(250, 110, 200, 30);
        createClerkButton.setBounds(150, 160, 200, 30);
        adminResetUserPassword.setBounds(150, 210, 200, 30);
    }

    /**
     * Adds all components to the container for display in the admin portal UI.
     * @param none
     * @return none
     */
    public void addComponents() {
        container.add(adminMessage);
        container.add(signOutButton);
        container.add(newReservationButton);
        container.add(modifyReservationButton);
        container.add(createClerkButton);
        container.add(adminResetUserPassword);
    }

    /**
     * Creates and displays the admin portal UI with the FlatDarcula look and feel.
     * @param none
     * @return none
     */
    public void createAndShowGui() {
        // Set Look and Feel of UI to FlatDarcula
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }

        adminPortalUI frame = new adminPortalUI();
        frame.setTitle("Admin Portal");
        frame.setVisible(true);
        frame.setBounds(500, 100, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
