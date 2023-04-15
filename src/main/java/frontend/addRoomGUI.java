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

public class addRoomGUI extends JFrame implements ActionListener {

    private JTextField numField1;
    private JTextField numField2;
    private JButton submitButton;

    public addRoomGUI() {
        setTitle("Add Room");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel label1 = new JLabel("  Enter room number:  ");
        panel.add(label1);
        numField1 = new JTextField();
        panel.add(numField1);

        JLabel label2 = new JLabel("  Enter room type:  ");
        panel.add(label2);
        numField2 = new JTextField();
        panel.add(numField2);
        
        add(panel, BorderLayout.NORTH);

        submitButton = new JButton("Add Room");
        submitButton.addActionListener(this);

        add(submitButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {

            try {

                String[] fields = { numField1.getText(), numField2.getText() };
                Room r = new Room(fields);

                //TODO: add room to database

                numField1.setText("");
                numField2.setText("");

                JOptionPane.showMessageDialog(this, "Room " + r.getNumber() + " added!", "Success", 1);
            } 
            catch (Exception ex) {

                System.out.println("INPUT ERROR");

                JOptionPane.showMessageDialog(this, "Please enter:\n    Room number: an integer\n    Room type: (doubleQueen, singleKing, suite)", "INPUT ERROR", 0);
            }
        }
    }

    public static void main(String[] args) {

        addRoomGUI ui = new addRoomGUI();
    }
}