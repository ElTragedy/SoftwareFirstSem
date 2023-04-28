package frontend.UI;

/*
 * This code uses a lot of functions like "Create User" and "create reservation"
 * which will need to communicate with the UIBlackBox. This is a TODO: connect
 * this to the UIBlackBox.
 */

import frontend.UIBlackBox;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class accountPortalUI extends JFrame implements ActionListener {
    // Main Container
    private Container container;
    private JPanel panel;

    // Labels to identify the fields
    private JLabel greetingLabel;

    // Button for confirmation
    private JButton signOutButton;
    private JButton newReservationButton;
    private JButton cancelReservationButton;

    // Table Attributes
    private JTable table;


    //  Constructor
    public accountPortalUI() {
        UIBlackBox.saveAll();

        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder()));

        String[] columnHeader = {"Room Number", "Room Size"};
        String[][] data = {
                {"123", "Suite"},
                {"69", "Single King"},
                {"20", "Double King"}
        };
        TableModel model = new DefaultTableModel(data, columnHeader) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 50));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(false);

        panel.add(new JScrollPane(table));


        // Set All Components
        greetingLabel = new JLabel("Hello, " + UIBlackBox.getCurrentAccount().getFirstName()); // TODO: Add Hello, "user's name"
        greetingLabel.setFont(new Font("Barlow", Font.BOLD, 30));

        // Add and overwrite sign out button
        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit the program?", "Exit Program Message Box",
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
                newReservationUI newReservationUI = new newReservationUI();
                newReservationUI.createAndShowGui();
                dispose();
            }
        });

        cancelReservationButton = new JButton("Cancel Reservation");
        //cancelReservationButton.addActionListener();

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
        panel.setBounds(50, 90, 500, 100);
    }

    public void addComponents() {
        container.add(greetingLabel);
        container.add(signOutButton);
        //container.add(newReservationButton);
        container.add(panel);
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
