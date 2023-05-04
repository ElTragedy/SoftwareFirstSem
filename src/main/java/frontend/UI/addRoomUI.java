package frontend.UI;

import com.formdev.flatlaf.FlatDarculaLaf;
import frontend.UIBlackBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addRoomUI extends JFrame implements ActionListener {

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

    public addRoomUI() {
        backButton = new JButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    clerkPortalUI clerkPortalUI = new clerkPortalUI();
                    clerkPortalUI.createAndShowGui();
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

    public void setBounds(){
        submitButton.setBounds(50, 110, 200, 30);
        backButton.setBounds(250, 110, 200, 30);
    }

    public void addComponents(){
        container.add(submitButton);
        container.add(backButton);
    }

    public void createAndShowGui() {
        // Set Look and Feel of UI to FlatDarcula
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }

        addRoomUI frame = new addRoomUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    public static class availableRooms extends JDialog {
        private JTable table;

        public availableRooms(JTable owner) {
            super(SwingUtilities.windowForComponent(owner));
            table = owner;
            createGUI();
        }

        private void createGUI() {
            setPreferredSize(new Dimension(600, 400));
            setTitle(getClass().getSimpleName());
            JPanel listPane = new JPanel();
            listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
            JLabel label = new JLabel("Hello:");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPane.add(label);
            int row = table.getSelectedRow();
            JLabel dataLabel = null;
            if (row < 0) {
                dataLabel = new JLabel("no row selected");
            } else {
                dataLabel = new JLabel(table.getModel().getValueAt(row, 0) + " " + table.getModel().getValueAt(row, 1));
            }
            dataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPane.add(dataLabel);
            add(listPane);
            pack();
            setLocationRelativeTo(getParent());
            JButton addButton = new JButton("Add row");
            addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((DefaultTableModel) table.getModel()).addRow(new Object[]{"Coolio", "Noman", "Karate", 1, true});
                    dispose();
                    JOptionPane.showMessageDialog(table, "Added new record");
                }
            });
            listPane.add(addButton);

        }

        @Override
        public void dispose() {
            super.dispose();
        }
    }
}