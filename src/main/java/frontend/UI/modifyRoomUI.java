package frontend.UI;

import backend.Room;
import frontend.UIBlackBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class modifyRoomUI extends JFrame {

    // Main Container
    private Container container;
    private JButton backButton;

    private JComboBox<String> numField2;
    private JComboBox<String> roomConditionField;
    private JComboBox<String> roomStatusField;


    private JTextField numField1;
    //private JTextField numField2;
    //private JTextField roomConditionField;
    //private JTextField roomStatusField;
    private JButton submitButton;

    public modifyRoomUI(String roomNum) {
        backButton = new JButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clerkPortalUI clerkPortalUI = new clerkPortalUI();
                clerkPortalUI.createAndShowGui();
                dispose();
            }
        });

        setTitle("Modify Room");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(4, 4));


        numField2 = new JComboBox<>(new String[]{"doubleQueen", "singleKing", "suite"});
        numField2.setSelectedItem(UIBlackBox.getRoom(Integer.parseInt(roomNum)).getRoomType());

        roomConditionField = new JComboBox<>(new String[]{"smoking", "nonSmoking"});
        String roomCondition = String.valueOf(UIBlackBox.getRoom(Integer.parseInt(roomNum)).getRoomCondition());
        roomConditionField.setSelectedItem(roomCondition);


        roomStatusField = new JComboBox<>(new String[]{"available", "occupied", "reserved"});
        String roomStatus = String.valueOf(UIBlackBox.getRoom(Integer.parseInt(roomNum)).getRoomStatus());
        roomStatusField.setSelectedItem(roomStatus);

        JLabel label1 = new JLabel("  Enter room number:  ");
        panel.add(label1);
        numField1 = new JTextField();
        numField1.setText(roomNum);
        panel.add(numField1);

        JLabel label2 = new JLabel("  Enter room type:  ");
        panel.add(label2);
        //numField2 = new JTextField();
        panel.add(numField2);

        JLabel label3 = new JLabel("  Enter room condition:  ");
        panel.add(label3);
        //roomConditionField = new JTextField();
        panel.add(roomConditionField);

        JLabel label4 = new JLabel("  Enter room status:  ");
        panel.add(label4);
        //roomStatusField = new JTextField();
        panel.add(roomStatusField);


        add(panel, BorderLayout.NORTH);

        submitButton = new JButton("Add Room");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submitButton) {

                    //try {

                    //String[] fields = { numField1.getText(), numField2.getText() };
                    //Room r = new Room(fields);
                    int a = Integer.parseInt(numField1.getText());
                    String roomType = (String) numField2.getSelectedItem();
                    String roomCondition = (String) roomConditionField.getSelectedItem();
                    String roomStatus = (String) roomStatusField.getSelectedItem();

                    Room newRoom = new Room(roomType, a, roomCondition, roomStatus);

                    UIBlackBox.updateRoom(Integer.parseInt(roomNum), newRoom);

                    numField1.setText("");
                    //numField2.setText("");

                    JOptionPane.showMessageDialog(null, "Room " + a + " added!", "Success", 1);
                    UIBlackBox.saveAll();
                    clerkPortalUI clerkPortalUI = new clerkPortalUI();
                    clerkPortalUI.createAndShowGui();
                    dispose();
                    UIBlackBox.saveAll();
                    //}
                    //catch (Exception ex) {

                    //System.out.println("INPUT ERROR");

                    //JOptionPane.showMessageDialog(this, "Please enter:\n    Room number: an integer\n    Room type: (doubleQueen, singleKing, suite)", "INPUT ERROR", 0);
                    //}
                }
            }
        });


        add(submitButton, BorderLayout.SOUTH);
        setVisible(true);

        //add and set container
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();

    }

//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == submitButton) {
//
//            //try {
//
//            //String[] fields = { numField1.getText(), numField2.getText() };
//            //Room r = new Room(fields);
//            int a = Integer.parseInt(numField1.getText());
//            String roomType = (String) numField2.getSelectedItem();
//            String roomCondition = (String) roomConditionField.getSelectedItem();
//            String roomStatus = (String) roomStatusField.getSelectedItem();
//
//            Room newRoom = new Room(roomType, a, roomCondition, roomStatus);
//
//            UIBlackBox.updateRoom();
//
//            numField1.setText("");
//            //numField2.setText("");
//
//            JOptionPane.showMessageDialog(this, "Room " + a + " added!", "Success", 1);
//            UIBlackBox.saveAll();
//            //}
//            //catch (Exception ex) {
//
//            //System.out.println("INPUT ERROR");
//
//            //JOptionPane.showMessageDialog(this, "Please enter:\n    Room number: an integer\n    Room type: (doubleQueen, singleKing, suite)", "INPUT ERROR", 0);
//            //}
//        }
//    }

    public void createAndShowGui() {
        addRoomUI frame = new addRoomUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    public void setBounds() {
        submitButton.setBounds(50, 110, 200, 30);
        backButton.setBounds(250, 110, 200, 30);
    }

    public void addComponents() {
        container.add(submitButton);
        container.add(backButton);
    }

//    public static void main(String[] args) {
//
//        //addRoomGUI ui = new addRoomGUI();
//    }
}