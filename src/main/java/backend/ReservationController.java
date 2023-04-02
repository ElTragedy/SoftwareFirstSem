package backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReservationController extends JFrame implements ActionListener {

    static JFrame frame;

    static JLabel startLabel;

    static Reservation reservation;

    static ReservationDatabase resDatabase;

    ReservationController() {};

    public static void main(String[] args) {
        createWindow();
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame){
        ReservationController r = new ReservationController();

        //title
        JLabel header = new JLabel("Reserve a Room");
        header.setFont(new Font("Calibri", Font.BOLD, 20));
        header.setBounds(300, 50, 200, 50);

        //start date
        startLabel = new JLabel("Enter Start Date:");
        startLabel.setBounds(150,100,100,20);

        JTextField startDate = new JFormattedTextField("MM/dd/yyyy");
        startDate.setBounds(300,100,100,20);
        startDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startDate.setText("");
            }
        });

        //end date
        JLabel endLabel = new JLabel("Enter End Date:");
        endLabel.setBounds(150,150,100,20);

        JTextField endDate = new JFormattedTextField("MM/dd/yyyy");
        endDate.setBounds(300,150,100,20);
        endDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                endDate.setText("");
            }
        });


        //bed option list
        JLabel bedSize = new JLabel("Bed Type:");
        bedSize.setBounds(150, 200, 100, 20);

        String[] bedOptions = {"Select", "doubleQueen", "singleKing", "suite"};
        JComboBox bedTypes = new JComboBox(bedOptions);
        bedTypes.setBounds(300, 200, 100, 20);
        bedTypes.setSelectedIndex(0);
//        bedTypes.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JComboBox cb = (JComboBox)e.getSource();
//                String bedType = (String)cb.getSelectedItem();
//                cb.setSelectedIndex();
//            }
//        });


        //reserve button
        JButton reserve = new JButton("Reserve Room");
        reserve.setBounds(300, 300, 100, 40);
        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String []data = new String[5];
                data[0] = "1";
                data[1] = "username";
                data[2] = startDate.getText();
                data[3] = endDate.getText();
                data[4] = (String) bedTypes.getSelectedItem();
                try {
                    reservation = new Reservation(data);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                //resDatabase.add(reservation);
            }
        });

        JPanel panel = new JPanel();
//        LayoutManager layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
//        panel.setLayout(layout);

        panel.setLayout(null);

        panel.add(header);
        panel.add(startLabel);
        panel.add(startDate);
        panel.add(endLabel);
        panel.add(endDate);
        panel.add(bedSize);
        panel.add(bedTypes);
        panel.add(reserve);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}