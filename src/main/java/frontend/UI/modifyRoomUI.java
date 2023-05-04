package frontend.UI;

import backend.Room;
import com.formdev.flatlaf.FlatDarculaLaf;
import frontend.UIBlackBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class modifyRoomUI extends JFrame implements ActionListener {

    // Main Container
    private Container container;
    private JButton backButton;
    private JLabel roomNumberLabel, roomSizeLabel, roomTypeLabel, roomStatusLabel;

    private JComboBox<String> roomSizeField;
    private JComboBox<String> roomConditionField;
    private JComboBox<String> roomStatusField;

    private JTextField roomNumberField;
    private JButton submitButton;

    public modifyRoomUI() {
        // Implement back button
        ImageIcon imageIcon = new ImageIcon("src/main/resources/Frontend_Resources/backButton.png");
        Image image = imageIcon.getImage().getScaledInstance(30, 30,  Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(image);
        backButton = new JButton(imageIcon);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clerkPortalUI clerkPortalUI = new clerkPortalUI();
                clerkPortalUI.createAndShowGui();
                dispose();
            }
        });

        // Initialize room fields
        roomNumberLabel = new JLabel("Enter room number: ");
        roomNumberField = new JTextField();

        roomSizeLabel = new JLabel("Enter room type:");
        roomSizeField = new JComboBox<>(new String[]{"doubleQueen", "singleKing", "suite"});

        roomTypeLabel = new JLabel("Enter room condition:");
        roomStatusLabel = new JLabel("Enter room status:");

        roomConditionField = new JComboBox<>(new String[]{"smoking", "nonSmoking"});
        roomStatusField = new JComboBox<>(new String[]{"available", "occupied", "reserved"});


        // Implement submit button
        submitButton = new JButton("Add Room");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submitButton) {
                    int a = Integer.parseInt(roomNumberField.getText());
                    String roomType = (String) roomSizeField.getSelectedItem();
                    String roomCondition = (String) roomConditionField.getSelectedItem();
                    String roomStatus = (String) roomStatusField.getSelectedItem();

                    Room newRoom = new Room(roomType, a, roomCondition, roomStatus);

                    UIBlackBox.updateRoom(a, newRoom);

                    roomNumberField.setText("");


                    JOptionPane.showMessageDialog(null, "Room " + a + " Modified!", "Success", 1);
                    UIBlackBox.saveAll();
                    clerkPortalUI clerkPortalUI = new clerkPortalUI();
                    clerkPortalUI.createAndShowGui();
                    dispose();
                    UIBlackBox.saveAll();
                }
            }
        });

        // Add and set container
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
    }

    public void setBounds(){
        backButton.setBounds(0, 0, 30, 30);

        roomNumberLabel.setBounds(30, 50, 150, 30);
        roomNumberField.setBounds(180, 50, 180, 30);

        roomSizeLabel.setBounds(30, 90, 150, 30);
        roomSizeField.setBounds(180, 90, 180, 30);

        roomTypeLabel.setBounds(30, 130, 150, 30);
        roomConditionField.setBounds(180, 130, 150, 30);

        roomStatusLabel.setBounds(30, 170, 150, 30);
        roomStatusField.setBounds(180, 170, 150, 30);

        submitButton.setBounds(180, 210, 150, 30);
    }

    public void addComponents(){
        container.add(backButton);

        container.add(roomNumberLabel);
        container.add(roomNumberField);

        container.add(roomSizeLabel);
        container.add(roomSizeField);

        container.add(roomTypeLabel);
        container.add(roomConditionField);

        container.add(roomStatusLabel);
        container.add(roomStatusField);

        container.add(submitButton);
    }

    public static void createAndShowGui() {
        // Set Look and Feel of UI to FlatDarcula
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }
        modifyRoomUI frame = new modifyRoomUI();
        frame.setTitle("Modify Room");
        frame.setBounds(500, 100, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}