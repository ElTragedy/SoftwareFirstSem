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
        startLabel = new JLabel("Enter Start Date:");
        JLabel endLabel = new JLabel("Enter End Date:");

        JTextField startDate = new JFormattedTextField("MM/dd/yyyy");
        startDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startDate.setText("");
            }
        });
        JTextField endDate = new JFormattedTextField("MM/dd/yyyy");
        endDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                endDate.setText("");
            }
        });

        JButton reserve = new JButton("Reserve Room");
        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String []data = new String[5];
                data[0] = "1";
                data[1] = "username";
                data[2] = "false";
                data[3] = startDate.getText();
                data[4] = endDate.getText();
                try {
                    reservation = new Reservation(data);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                resDatabase.add(reservation);
            }
        });

        JPanel panel = new JPanel();
        LayoutManager layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(layout);

        panel.add(startLabel);
        panel.add(startDate);
        panel.add(endLabel);
        panel.add(endDate);
        panel.add(reserve);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}