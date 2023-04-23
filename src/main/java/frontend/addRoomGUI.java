package frontend;

/*
 * this code is for Adding a room but it currently does not add any rooms. We
 * TODO: need to connect this to the UIBlackBox. We should just make a
 * "addRoom" function and then we just pass in parameters from the GUI.
 */

import backend.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addRoomGUI extends JFrame implements ActionListener {

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

    public addRoomGUI() {
        backButton = new JButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountPortalGUI accountPortalGUI = new accountPortalGUI();
                accountPortalGUI.createAndShowGui();
                dispose();
            }
        });

        setTitle("Add Room");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(4, 4));


        numField2 = new JComboBox<>(new String[]{"doubleQueen", "singleKing", "suite"});
        roomConditionField = new JComboBox<>(new String[]{"smoking", "nonSmoking"});
        roomStatusField = new JComboBox<>(new String[]{"available", "occupied", "reserved"});


        JLabel label1 = new JLabel("  Enter room number:  ");
        panel.add(label1);
        numField1 = new JTextField();
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
        submitButton.addActionListener(this);


        add(submitButton, BorderLayout.SOUTH);
        setVisible(true);

        //add and set container
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();

        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {

            //try {

                //String[] fields = { numField1.getText(), numField2.getText() };
                //Room r = new Room(fields);
                int a = Integer.parseInt(numField1.getText());
                String roomType = (String) numField2.getSelectedItem();
                String roomCondition = (String) roomConditionField.getSelectedItem();
                String roomStatus = (String) roomStatusField.getSelectedItem();


                UIBlackBox.createRoom(roomType, a, roomCondition, roomStatus); 

                numField1.setText("");
                //numField2.setText("");

                JOptionPane.showMessageDialog(this, "Room " + a + " added!", "Success", 1);
                UIBlackBox.saveAll();
            //} 
            //catch (Exception ex) {

                //System.out.println("INPUT ERROR");

                //JOptionPane.showMessageDialog(this, "Please enter:\n    Room number: an integer\n    Room type: (doubleQueen, singleKing, suite)", "INPUT ERROR", 0);
            //}
        }
    }

    public void createAndShowGui() {
        addRoomGUI frame = new addRoomGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    public void setBounds(){
        submitButton.setBounds(50, 110, 200, 30);
        backButton.setBounds(250, 110, 200, 30);
    }

    public void addComponents(){
        container.add(submitButton);
        container.add(backButton);
    }

    public static void main(String[] args) {

        //addRoomGUI ui = new addRoomGUI();
    }
}