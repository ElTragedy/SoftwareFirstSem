package frontend.UI;

import backend.Account;
import com.formdev.flatlaf.FlatDarculaLaf;
import frontend.UIBlackBox;
import frontend.table.AvaliableRoomTable;
import frontend.utilities.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Random;

public class editProfileUI extends JFrame{
    private Account account;
    private JButton backButton;
    private JLabel message;
    private JLabel firstNameLabel, lastNameLabel, dobLabel, sexLabel, dobFormat;
    private JTextField firstNameField, lastNameField;
    private JRadioButton sexMale, sexFemale, sexUnassigned;
    private ButtonGroup sexGroup;
    private JLabel emailLabel, mobileNoLabel;
    private JTextField emailField, mobileNoField;
    private JLabel passwordLabel, rePasswordLabel;
    private JPasswordField passwordField, rePasswordField;
    private JLabel addressLabel;
    private JTextField addressField;
    private JLabel cityLabel;
    private JTextField cityField;
    private JLabel stateLabel;
    private JComboBox<String> stateList;
    private JLabel zipLabel;
    private JTextField zipField;
    private JLabel countryLabel;
    private JComboBox<String> countryList;
    private JButton registerButton;
    private Container container;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;

    public editProfileUI() {
        // Implement Back Button
        backButton = new JButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clerkPortalUI clerkPortalUI = new clerkPortalUI();
                clerkPortalUI.createAndShowGui();
                dispose();
            }
        });

        ImageIcon imageIcon = new ImageIcon("src/main/resources/Frontend_Resources/backButton.png");
        Image image = imageIcon.getImage().getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        backButton.setIcon(imageIcon);

        // Set header for window
        message = new JLabel("Edit Profile");
        message.setFont(new Font("Barlow", Font.BOLD, 20));

        account = UIBlackBox.getCurrentAccount();

        // Add name labels and fields
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        firstNameField = new JTextField(account.getFirstName());
        lastNameField = new JTextField(account.getLastName());


        // Add DOB labels and fields
        dobLabel = new JLabel("DOB");
        UtilDateModel model = new UtilDateModel();

        //pre-load info
        String DOB = account.getDOB();
        if (DOB != null) {
            String[] dobArray = DOB.split("-");
            model.setDate(Integer.parseInt(dobArray[0]), Integer.parseInt(dobArray[1]), Integer.parseInt(dobArray[2]));
        }


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
        sexMale = new JRadioButton("Male");
        sexFemale = new JRadioButton("Female");
        sexUnassigned = new JRadioButton("Unassigned");
        sexGroup = new ButtonGroup();
        sexGroup.add(sexMale);
        sexGroup.add(sexFemale);
        sexGroup.add(sexUnassigned);

        //pre-load info
        String sex = account.getSex();
        if (sex != null) {
            switch (sex) {
                case "Male":
                    sexGroup.setSelected(sexMale.getModel(), true);
                    break;
                case "Female":
                    sexGroup.setSelected(sexFemale.getModel(), true);
                    break;
                case "Unassigned":
                    sexGroup.setSelected(sexUnassigned.getModel(), true);
                    break;
            }
        }


        // Add contact/login info labels and fields
        emailLabel = new JLabel("Email");
        emailField = new JTextField(account.getEmail());
        mobileNoLabel = new JLabel("Mobile No");
        mobileNoField = new JTextField(account.getPhoneNumber());
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(account.getPassword());
        rePasswordLabel = new JLabel("Re Password");
        rePasswordField = new JPasswordField(account.getPassword());

        // Add address/location labels and fields
        addressLabel = new JLabel("Address");
        addressField = new JTextField(account.getAddress());
        cityLabel = new JLabel("City");
        cityField = new JTextField(account.getCity());

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

        stateList.setSelectedItem(account.getState());

        // Add zip/country labels and fields
        zipLabel = new JLabel("Zipcode");
        zipField = new JTextField(account.getZipcode());
        countryLabel = new JLabel("Country");
        countryList = new JComboBox<>();
        countryList.addItem("United States");
        countryList.setSelectedItem(account.getCountry());

        // Add register button
        registerButton = new JButton("Update");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //create a random 4 digit number for the account ID
                String id = account.getId();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String DOB = datePicker.getJFormattedTextField().getText();
                String sex = sexMale.isSelected() ? "Male" : (sexFemale.isSelected() ? "Female" : "Unassigned");
                String phoneNumber = mobileNoField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String address = addressField.getText();
                String zipcode = zipField.getText();
                String city = cityField.getText();
                String state = stateList.getSelectedItem().toString();
                String country = countryList.getSelectedItem().toString();


                Account newAccount = new Account(id, firstName, lastName, DOB, sex, phoneNumber, email, password, address, zipcode, city, state, country, "clerk");
                boolean success = UIBlackBox.createAccount(newAccount.getId(), newAccount.getFirstName(), newAccount.getLastName(), newAccount.getDOB(), newAccount.getSex(), newAccount.getPhoneNumber(), newAccount.getEmail(), newAccount.getPassword(), newAccount.getAddress(), newAccount.getZipcode(), newAccount.getCity(), newAccount.getState(), newAccount.getCountry(), newAccount.getAccess());
                if (success){
                    UIBlackBox.setCurrentAccount(newAccount);
                    UIBlackBox.deleteAccount(account);
                    UIBlackBox.saveAll();
                    clerkPortalUI clerkPortalUI = new clerkPortalUI();
                    clerkPortalUI.createAndShowGui();
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Account creation failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add and set container

        container = new JPanel();
        container.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scrollPane);

        setBounds();
        container.setPreferredSize(new Dimension(500, 800));
        addComponents();
    }

    // Sets all labels/fields bounds
    public void setBounds() {
        backButton.setBounds(0, 0, 30, 30);
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
        container.add(backButton);
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
    public void createAndShowGui() {
        // Set Look and Feel of UI to FlatDarcula
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }

        editProfileUI frame = new editProfileUI();
        frame.setTitle("Edit Profile");
        frame.setVisible(true);
        frame.setBounds(500, 15, 500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }
}
