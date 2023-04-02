package backend;

import java.awt.Container;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.*;

import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
public class addUserGUI extends JFrame {
    JLabel message;
    JLabel firstNameLabel, lastNameLabel, dobLabel, sexLabel, dobFormat;
    JTextField firstNameField, lastNameField;
    JRadioButton sexMale, sexFemale, sexUnassigned;
    ButtonGroup sexGroup;
    JLabel emailLabel, mobileNoLabel;
    JTextField emailField, mobileNoField;
    JLabel passwordLabel, rePasswordLabel;
    JPasswordField passwordField, rePasswordField;
    JLabel addressLabel;
    JTextField addressField;
    JLabel cityLabel;
    JTextField cityField;
    JLabel stateLabel;
    JComboBox<String> stateList;
    JLabel zipLabel;
    JTextField zipField;
    JLabel countryLabel;
    JComboBox<String> countryList;
    JButton registerButton;
    Container container;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;

    public addUserGUI() {
        // Set header for window
        message = new JLabel("Register a New User");
        message.setFont(new Font("Courier", Font.BOLD, 20));

        // Add name labels and fields
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        firstNameField = new JTextField();
        lastNameField = new JTextField();

        // Add DOB labels and fields
        dobLabel = new JLabel("DOB");
        UtilDateModel model = new UtilDateModel();
        model.setDate(1999, 01, 02);
        model.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        dobFormat = new JLabel("(yyyy-mm-dd)");

        // Add sex labels and fields
        sexLabel = new JLabel("Sex");
        sexMale = new JRadioButton("Male", true);
        sexFemale = new JRadioButton("Female");
        sexUnassigned = new JRadioButton("Unassigned");
        sexGroup = new ButtonGroup();
        sexGroup.add(sexMale);
        sexGroup.add(sexFemale);
        sexGroup.add(sexUnassigned);

        // Add contact/login info labels and fields
        emailLabel = new JLabel("Email");
        emailField = new JTextField();
        mobileNoLabel = new JLabel("Mobile No");
        mobileNoField = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        rePasswordLabel = new JLabel("Re Password");
        rePasswordField = new JPasswordField();

        // Add address/location labels and fields
        addressLabel = new JLabel("Address");
        addressField = new JTextField();
        cityLabel = new JLabel("City");
        cityField = new JTextField();

        // Add state drop down menu
        stateLabel = new JLabel("State");
        stateList = new JComboBox<String>();
        stateList.addItem("AL");
        stateList.addItem("AK");
        stateList.addItem("AZ");
        stateList.addItem("AR");
        stateList.addItem("CA");
        stateList.addItem("CO");
        stateList.addItem("CT");
        stateList.addItem("DE");
        stateList.addItem("FL");
        stateList.addItem("GA");
        stateList.addItem("HI");
        stateList.addItem("ID");
        stateList.addItem("IL");
        stateList.addItem("IN");
        stateList.addItem("IA");
        stateList.addItem("KS");
        stateList.addItem("KY");
        stateList.addItem("LA");
        stateList.addItem("ME");
        stateList.addItem("MD");
        stateList.addItem("MA");
        stateList.addItem("MI");
        stateList.addItem("MN");
        stateList.addItem("MS");
        stateList.addItem("MO");
        stateList.addItem("MT");
        stateList.addItem("NE");
        stateList.addItem("NV");
        stateList.addItem("NH");
        stateList.addItem("NJ");
        stateList.addItem("NM");
        stateList.addItem("NY");
        stateList.addItem("NC");
        stateList.addItem("ND");
        stateList.addItem("MP");
        stateList.addItem("OH");
        stateList.addItem("OK");
        stateList.addItem("OR");
        stateList.addItem("PA");
        stateList.addItem("RI");
        stateList.addItem("SC");
        stateList.addItem("SD");
        stateList.addItem("TN");
        stateList.addItem("TX");
        stateList.addItem("UT");
        stateList.addItem("VT");
        stateList.addItem("VA");
        stateList.addItem("WA");
        stateList.addItem("WV");
        stateList.addItem("WI");
        stateList.addItem("WY");

        // Add zip/country labels and fields
        zipLabel = new JLabel("Zipcode");
        zipField = new JTextField();
        countryLabel = new JLabel("Country");
        countryList = new JComboBox<>();
        countryList.addItem("United States");

        // Add register button
        registerButton = new JButton("Register");

        // Add and set container
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
    }

    // Sets all labels/fields bounds
    public void setBounds() {
        message.setBounds(50, 10, 600, 30);
        firstNameLabel.setBounds(50, 60, 100, 30);
        firstNameField.setBounds(130, 60, 200, 30);
        lastNameLabel.setBounds(50, 110, 100, 30);
        lastNameField.setBounds(130, 110, 200, 30);
        dobLabel.setBounds(50, 160, 100, 30);
        datePicker.setBounds(130, 160, 200, 30);
        dobFormat.setBounds(350, 160, 200, 30);
        sexLabel.setBounds(50, 210, 100, 30);
        sexMale.setBounds(130, 210, 100, 30);
        sexFemale.setBounds(240, 210, 100, 30);
        sexUnassigned.setBounds(350, 210, 100, 30);
        emailLabel.setBounds(50, 260, 100, 30);
        emailField.setBounds(130, 260, 200, 30);
        mobileNoLabel.setBounds(50, 310, 100, 30);
        mobileNoField.setBounds(130, 310, 200, 30);
        passwordLabel.setBounds(50, 360, 100, 30);
        passwordField.setBounds(130, 360, 200, 30);
        rePasswordLabel.setBounds(50, 410, 100, 30);
        rePasswordField.setBounds(130, 410, 200, 30);
        addressLabel.setBounds(50, 460, 100, 30);
        addressField.setBounds(130, 460, 200, 30);
        cityLabel.setBounds(50, 510, 100, 30);
        cityField.setBounds(130, 510, 200, 30);
        stateLabel.setBounds(50, 560, 100, 30);
        stateList.setBounds(130, 560, 200, 30);
        zipLabel.setBounds(50, 610, 100, 30);
        zipField.setBounds(130, 610, 200, 30);
        countryLabel.setBounds(50, 660, 100, 30);
        countryList.setBounds(130, 660, 200, 30);
        registerButton.setBounds(130, 710, 200, 30);
    }

    // Adds labels and fields to container
    public void addComponents() {
        container.add(message);
        container.add(firstNameLabel);
        container.add(firstNameField);
        container.add(lastNameLabel);
        container.add(lastNameField);
        container.add(dobLabel);
        container.add(datePicker);
        container.add(dobFormat);
        container.add(sexLabel);
        container.add(sexMale);
        container.add(sexFemale);
        container.add(sexUnassigned);
        container.add(emailLabel);
        container.add(emailField);
        container.add(mobileNoLabel);
        container.add(mobileNoField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(rePasswordLabel);
        container.add(rePasswordField);
        container.add(addressLabel);
        container.add(addressField);
        container.add(cityLabel);
        container.add(cityField);
        container.add(stateLabel);
        container.add(stateList);
        container.add(zipLabel);
        container.add(zipField);
        container.add(countryLabel);
        container.add(countryList);
        container.add(registerButton);
    }
    public static void main(String[] args) {
        addUserGUI frame = new addUserGUI();
        frame.setTitle("Create New Account");
        frame.setVisible(true);
        frame.setBounds(500, 100, 500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    // Class for formatting DOB information
    public class DateLabelFormatter extends AbstractFormatter {
        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
        @Override
        public Object stringToValue(String text) throws ParseException, ParseException {
            return dateFormatter.parseObject(text);
        }
        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }
}