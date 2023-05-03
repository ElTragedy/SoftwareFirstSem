package frontend.UI;

import backend.Reservation;
import com.formdev.flatlaf.FlatDarculaLaf;
import frontend.UIBlackBox;
import frontend.utilities.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class reservationUI extends JFrame{

    private Container container;
    private JButton reserve, backButton;
    private JComboBox bedTypes;
    private JLabel startLabel, endLabel, reservationLabel, header, bedSize, dateFormat, dateFormat1;
    private JDatePanelImpl datePanel, datePanel1;
    private JDatePickerImpl datePicker, datePicker1;
    private Reservation reservation;

    public static void main(String args[]){
        reservationUI reservationUI = new reservationUI();
        reservationUI.createAndShowGui();
    }

    public reservationUI(){
        backButton = new JButton();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UIBlackBox.getCurrentAccount().getAccess() == "clerk"){
                    clerkPortalUI clerkPortalUI = new clerkPortalUI();
                    clerkPortalUI.createAndShowGui();
                }
                else {
                    accountPortalUI accountPortalUI = new accountPortalUI();
                    accountPortalUI.createAndShowGui();
                }
                dispose();
            }
        });

        //title
        header = new JLabel("Reserve a Room");
        header.setFont(new Font("Calibri", Font.BOLD, 20));

        //start date
        startLabel = new JLabel("Enter Start Date:");
        UtilDateModel model1 = new UtilDateModel();
        Properties p1 = new Properties();
        p1.put("text.today", "Today");
        p1.put("text.month", "Month");
        p1.put("text.year", "Year");
        datePanel1 = new JDatePanelImpl(model1, p1);
        datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        dateFormat1 = new JLabel("(yyyy-MM-dd)");

        //end date
        endLabel = new JLabel("Enter End Date:");
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        dateFormat = new JLabel("(yyyy-MM-dd)");


        //bed option list
        bedSize = new JLabel("Bed Type:");

        String[] bedOptions = {"Select", "doubleQueen", "singleKing", "suite"};
        bedTypes = new JComboBox(bedOptions);
        bedTypes.setSelectedIndex(0);


        //reserve button
        reserve = new JButton("Reserve Room");
        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //when implemented use UIBlackBox.getCurrentAccount()
                if(true){
                    JOptionPane.showMessageDialog(null,"You have succefully reserved a Room!");
                }
            }
        });

        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
    }






    public reservationUI(String email){
        backButton = new JButton();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UIBlackBox.getCurrentAccount().getAccess() == "clerk"){
                    clerkPortalUI clerkPortalUI = new clerkPortalUI();
                    clerkPortalUI.createAndShowGui();
                }
                else {
                    accountPortalUI accountPortalUI = new accountPortalUI();
                    accountPortalUI.createAndShowGui();
                }
                dispose();
            }
        });

        //title
        header = new JLabel("Reserve a Room");
        header.setFont(new Font("Calibri", Font.BOLD, 20));

        //start date
        startLabel = new JLabel("Enter Start Date:");
        UtilDateModel model1 = new UtilDateModel();
        Properties p1 = new Properties();
        p1.put("text.today", "Today");
        p1.put("text.month", "Month");
        p1.put("text.year", "Year");
        datePanel1 = new JDatePanelImpl(model1, p1);
        datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        dateFormat1 = new JLabel("(yyyy-MM-dd)");

        //end date
        endLabel = new JLabel("Enter End Date:");
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        dateFormat = new JLabel("(yyyy-MM-dd)");


        //bed option list
        bedSize = new JLabel("Bed Type:");

        String[] bedOptions = {"Select", "doubleQueen", "singleKing", "suite"};
        bedTypes = new JComboBox(bedOptions);
        bedTypes.setSelectedIndex(0);


        //reserve button
        reserve = new JButton("Reserve Room");
        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //when implemented use UIBlackBox.getCurrentAccount()
                if(true){
                    JOptionPane.showMessageDialog(null,"You have succefully reserved a Room!");
                }
            }
        });

        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
    }






    public void setBounds(){
        backButton.setBounds(0,0, 30, 30);
        header.setBounds(200, 50, 200, 50);
        startLabel.setBounds(100,100,100,20);
        datePicker1.setBounds(200,100,200,30);
        endLabel.setBounds(100,150,100,20);
        datePicker.setBounds(200,150,200,30);
        bedSize.setBounds(100, 200, 100, 20);
        bedTypes.setBounds(200, 200, 100, 20);
        reserve.setBounds(200, 300, 100, 40);
    }

    public void addComponents(){
        container.add(backButton);
        container.add(header);
        container.add(startLabel);
        container.add(endLabel);
        container.add(bedSize);
        container.add(bedTypes);
        container.add(reserve);
        container.add(datePicker1);
        container.add(datePicker);
        container.add(dateFormat);
        container.add(dateFormat1);
    }

    public void createAndShowGui() {
        // Set Look and Feel of UI to FlatDarcula
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }

        reservationUI frame = new reservationUI();
        frame.setTitle("Make a Reservation");
        frame.setVisible(true);
        frame.setBounds(500,15, 500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

}