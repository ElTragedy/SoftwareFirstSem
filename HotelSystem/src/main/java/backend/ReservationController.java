package backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
        JLabel username = new JLabel("Enter Username:");
        JTextField userText = new JTextField();

        String[] bedSizes = { "King", "Queen", "2 Queens", "Double", "2 Doubles" };

        JLabel bedLabel = new JLabel("Select Room Style:");
        JComboBox bedSelection = new JComboBox(bedSizes);
        bedSelection.setSelectedIndex(4);
        bedSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String bedSize = (String)cb.getSelectedItem();
            }
        });

        startLabel = new JLabel("Enter Start Date:");
        JTextField startDate = new JFormattedTextField("MM/dd/yyyy");
        startDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startDate.setText("");
            }
        });

        JLabel endLabel = new JLabel("Enter End Date:");
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
                try {
                    resDatabase = new ReservationDatabase();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                String []data = new String[6];
                data[0] = "1";
                data[1] = userText.getText();
                data[2] = "true";
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
        LayoutManager layout = new GridLayout(5,2 );
        panel.setLayout(layout);

        panel.add(username);
        panel.add(userText);
        panel.add(bedLabel);
        panel.add(bedSelection);
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