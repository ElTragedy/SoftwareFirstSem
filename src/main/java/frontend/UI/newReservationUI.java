package frontend.UI;

import backend.Account;
import backend.Reservation;
import backend.Room;
import com.formdev.flatlaf.FlatDarculaLaf;
import frontend.UIBlackBox;
import frontend.table.AvaliableRoomTable;
import frontend.utilities.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class newReservationUI extends JFrame {
    private JButton backButton;
    private JLabel message;
    private JLabel startDateLabel, endDateLabel;
    private JDatePanelImpl startDatePanel, endDatePanel;
    private JDatePickerImpl startDatePicker, endDatePicker;
    private JLabel roomTypeLabel;
    private JComboBox<String> roomTypeList;
    private JButton reserveRoom;

    private JScrollPane scrollPane;
    private AvaliableRoomTable avaliableRoomTable;
    private JButton confirmButton;

    private JFrame parentFrame;
    private Container container;

    private Account account;

    public newReservationUI(JFrame parent) {

        parentFrame = parent;

        // Implement Back Button
        backButton = new JButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (Objects.equals(UIBlackBox.getCurrentAccount().getAccess(), "clerk")) {
//                    clerkPortalUI clerkPortalUI = new clerkPortalUI();
//                    clerkPortalUI.createAndShowGui();
//                } else {
//                    accountPortalUI accountPortalUI = new accountPortalUI();
//                    accountPortalUI.createAndShowGui();
//                }
                parentFrame.setVisible(true);
                dispose();
            }
        });
        // Add Image To Back Button
        ImageIcon imageIcon = new ImageIcon("src/main/resources/Frontend_Resources/backButton.png");
        Image image = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        backButton.setIcon(imageIcon);

        // Set header for window
        message = new JLabel("Schedule Your Stay");
        message.setFont(new Font("Barlow", Font.BOLD, 20));

        // Start Date Panel
        startDateLabel = new JLabel("Start Date");
        UtilDateModel startDateModel = new UtilDateModel();
        startDateModel.setDate(2023, 04, 18);
        startDateModel.setSelected(true);
        Properties startProperties = new Properties();
        startProperties.put("text.today", "Today");
        startProperties.put("text.month", "Month");
        startProperties.put("text.year", "Year");
        startDatePanel = new JDatePanelImpl(startDateModel, startProperties);
        startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());

        // End Date Panel
        endDateLabel = new JLabel("End Date");
        UtilDateModel endDateModel = new UtilDateModel();
        endDateModel.setDate(2023, 04, 18);
        endDateModel.setSelected(true);
        Properties endProperties = new Properties();
        endProperties.put("text.today", "Today");
        endProperties.put("text.month", "Month");
        endProperties.put("text.year", "Year");
        endDatePanel = new JDatePanelImpl(endDateModel, startProperties);
        endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());

        // Room Type Drop Down
        roomTypeLabel = new JLabel("Room Size");
        roomTypeList = new JComboBox<String>();
        roomTypeList.addItem("Suite");
        roomTypeList.addItem("Single King");
        roomTypeList.addItem("Double Queen");

        // Add Table
        avaliableRoomTable = new AvaliableRoomTable();

        // Add register button
        confirmButton = new JButton("Check Availability");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avaliableRoomTable.updateTable(UIBlackBox.getAvailableRooms(
                        (Date) startDatePicker.getModel().getValue(),
                        (Date) endDatePicker.getModel().getValue(),
                        roomTypeList.getModel().getSelectedItem().toString()));
            }
        });

        // reserve room
        reserveRoom = new JButton("Reserve Room");
        reserveRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

                Date startDate = (Date) startDatePicker.getModel().getValue();
                String formattedDate = dateFormatter.format(startDate);

                Account account = UIBlackBox.getCurrentAccount();
                String subject = "Room Reserved";
                String message = "Hello " + account.getFirstName() + " " + account.getLastName() + ",\n\n" +
                        "Your reservation has been confirmed for " + formattedDate + ".";

                int roomNumber = Integer.parseInt((String) avaliableRoomTable.getTable().getValueAt(avaliableRoomTable.getTable().getSelectedRow(), 0));
                UIBlackBox.createReservation(UIBlackBox.getCurrentAccount().getEmail(),
                        roomNumber,
                        false,
                        (Date) startDatePicker.getModel().getValue(),
                        (Date) endDatePicker.getModel().getValue());


                try {
                    UIBlackBox.sendEmail(UIBlackBox.getCurrentAccount().getEmail(), subject, message);
                } catch (MessagingException ex) {
                    JOptionPane.showMessageDialog(container, "Could not successfully deliver confirmation email.",
                            "Email Service Error", JOptionPane.ERROR_MESSAGE);
                }
                ((accountPortalUI) parent).updateTable();
            }
        });


        // Add and set container
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
    }


    public newReservationUI(String email, char[] password) {
        // Implement Back Button
        backButton = new JButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(UIBlackBox.getCurrentAccount().getAccess(), "clerk")) {
                    clerkPortalUI clerkPortalUI = new clerkPortalUI();
                    clerkPortalUI.createAndShowGui();
                } else {
                    accountPortalUI accountPortalUI = new accountPortalUI();
                    accountPortalUI.createAndShowGui();
                }
                dispose();
            }
        });
        // Add Image To Back Button
        ImageIcon imageIcon = new ImageIcon("src/main/resources/Frontend_Resources/backButton.png");
        Image image = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        backButton.setIcon(imageIcon);

        // Set header for window
        message = new JLabel("Schedule Your Stay");
        message.setFont(new Font("Barlow", Font.BOLD, 20));

        // Start Date Panel
        startDateLabel = new JLabel("Start Date");
        UtilDateModel startDateModel = new UtilDateModel();
        startDateModel.setDate(2023, 04, 18);
        startDateModel.setSelected(true);
        Properties startProperties = new Properties();
        startProperties.put("text.today", "Today");
        startProperties.put("text.month", "Month");
        startProperties.put("text.year", "Year");
        startDatePanel = new JDatePanelImpl(startDateModel, startProperties);
        startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());

        // End Date Panel
        endDateLabel = new JLabel("End Date");
        UtilDateModel endDateModel = new UtilDateModel();
        endDateModel.setDate(2023, 04, 18);
        endDateModel.setSelected(true);
        Properties endProperties = new Properties();
        endProperties.put("text.today", "Today");
        endProperties.put("text.month", "Month");
        endProperties.put("text.year", "Year");
        endDatePanel = new JDatePanelImpl(endDateModel, startProperties);
        endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());

        // Room Type Drop Down
        roomTypeLabel = new JLabel("Room Size");
        roomTypeList = new JComboBox<String>();
        roomTypeList.addItem("Suite");
        roomTypeList.addItem("Single King");
        roomTypeList.addItem("Double Queen");

        // Add Table
        avaliableRoomTable = new AvaliableRoomTable();

        // Add register button
        confirmButton = new JButton("Check Availability");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avaliableRoomTable.updateTable(UIBlackBox.getAvailableRooms(
                        (Date) startDatePicker.getModel().getValue(),
                        (Date) endDatePicker.getModel().getValue(),
                        roomTypeList.getModel().getSelectedItem().toString()));
            }
        });

        // Reserve room
        reserveRoom = new JButton("Reserve Room");
        reserveRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

                Date startDate = (Date) startDatePicker.getModel().getValue();
                String formattedDate = dateFormatter.format(startDate);

                Account account = UIBlackBox.getAccount(email, password);
                String subject = "Room Reserved";
                String message = "Hello " + account.getFirstName() + " " + account.getLastName() + ",\n\n" +
                        "Your reservation has been confirmed for " + formattedDate + ".";

                try {
                    UIBlackBox.sendEmail(UIBlackBox.getCurrentAccount().getEmail(), subject, message);
                } catch (MessagingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        // Add and set container
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
    }


    public void setBounds() {
        backButton.setBounds(0, 0, 30, 30);

        message.setBounds(50, 10, 600, 30);

        startDateLabel.setBounds(50, 60, 100, 30);
        startDatePicker.setBounds(130, 60, 200, 30);

        endDateLabel.setBounds(50, 110, 100, 30);
        endDatePicker.setBounds(130, 110, 200, 30);

        roomTypeLabel.setBounds(50, 160, 100, 30);
        roomTypeList.setBounds(130, 160, 200, 30);

        avaliableRoomTable.setBounds(350, 60, 400, 400);

        confirmButton.setBounds(130, 210, 200, 30);
        reserveRoom.setBounds(130, 260, 200, 30);
    }

    public void addComponents() {
        container.add(backButton);
        container.add(message);

        container.add(startDateLabel);
        container.add(startDatePicker);

        container.add(endDateLabel);
        container.add(endDatePicker);

        container.add(roomTypeLabel);
        container.add(roomTypeList);

        container.add(avaliableRoomTable);

        container.add(confirmButton);
        container.add(reserveRoom);
    }

    public void createAndShowGui() {
        // Set Look and Feel of UI to FlatDarcula
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }
        newReservationUI frame = new newReservationUI(parentFrame);
        frame.setTitle("Create New Reservation");
        frame.setVisible(true);
        frame.setBounds(500, 15, 800, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }
}
