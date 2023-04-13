package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class welcomeGUI extends JFrame implements ActionListener {
    // Main Container
    private Container container;

    // Labels to identify the fields
    private JLabel welcomeLabel;
    private JLabel promptLabel;

    // Button for confirmation
    private JButton returningUserButton;
    private JButton newUserButton;

    public welcomeGUI() { // OVERRIDE BUTTONS BY LAMBDA FUNCTIONS
        // Set All Components
        welcomeLabel = new JLabel("Welcome" );
        welcomeLabel.setFont(new Font("Barlow", Font.BOLD, 30));

        promptLabel = new JLabel("Would you like to sign in or create a new account?");
        promptLabel.setFont(new Font("Barlow", Font.PLAIN, 15));

        returningUserButton = new JButton("Sign In");
        returningUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginGUI loginGUI = new loginGUI();
                loginGUI.createAndShowGui();
                dispose();
            }
        });

        newUserButton = new JButton("Create Account");
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccountGUI createAccountGUI = new createAccountGUI();
                createAccountGUI.createAndShowGui();
                dispose();
            }
        });

        // Add and set container
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
    }

    // Sets all labels/fields bounds
    public void setBounds() {
        welcomeLabel.setBounds(175, 10, 250, 30);
        promptLabel.setBounds(100, 60, 350, 30);
        returningUserButton.setBounds(50, 110, 200, 30);
        newUserButton.setBounds(250, 110, 200, 30);
    }

    public void addComponents() {
        container.add(welcomeLabel);
        container.add(promptLabel);
        container.add(returningUserButton);
        container.add(newUserButton);
    }

    public void createAndShowGui() {
        welcomeGUI frame = new welcomeGUI();
        frame.setTitle("Welcome Window");
        frame.setVisible(true);
        frame.setBounds(500, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    public static void main(String[] args) {
        welcomeGUI frame = new welcomeGUI();
        frame.setTitle("Welcome Window");
        frame.setVisible(true);
        frame.setBounds(500, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
