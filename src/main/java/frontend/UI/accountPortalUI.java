package frontend.UI;

/*
 * This code uses a lot of functions like "Create User" and "create reservation"
 * which will need to communicate with the UIBlackBox. This is a TODO: connect
 * this to the UIBlackBox.
 */

import frontend.UIBlackBox;
//import frontend.table.ReservationStatusTable;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
    private JButton newReservationButton;
    private JButton cancelReservationButton;

    // Table Attributes
    private DefaultTableModel model;
    private JTable reservationStatusTable;
    private TableRowSorter<DefaultTableModel> sorter;
    private JScrollPane scrollPane;

    private String[] columnNames = {
            "Room Number",
            "Room Size"
    };
    private Object[][] data = {
            {"123", "Suite"},
            {"69", "Single King"},
            {"20", "Double King"}
    };

    //  Constructor
    public accountPortalUI() {
        UIBlackBox.saveAll();

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

        // Initialize Table
        final Class<?>[] columnClass = new Class[] {
                String.class, String.class, String.class, Integer.class, Boolean.class
        };
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
            @Override
            public Class<?> getColumnClass(int columnIndex) { return columnClass[columnIndex]; }
        };
        sorter = new TableRowSorter<DefaultTableModel>(model);
        reservationStatusTable = new JTable(model);

        reservationStatusTable.setRowSorter(sorter);
        reservationStatusTable.setPreferredScrollableViewportSize(new Dimension(50, 50));
        reservationStatusTable.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(reservationStatusTable);
        //reservationStatusTable.add(scrollPane);

        TableFilterHeader filterHeader = new TableFilterHeader(reservationStatusTable, AutoChoices.ENABLED);
        reservationStatusTable.add(filterHeader);


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
        reservationStatusTable.setBounds(50, 90, 500, 50);
    }

    public void addComponents() {
        container.add(greetingLabel);
        container.add(signOutButton);
        container.add(newReservationButton);
        container.add(reservationStatusTable);
        //container.add(sorter);
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
