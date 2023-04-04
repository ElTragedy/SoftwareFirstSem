package backend;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;

public class AccountDatabase {
    private HashMap<String, Account> users;

    public AccountDatabase() throws IOException {
        users = new HashMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/Users.csv")));

        // skip header
        reader.readLine();

        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(",");

            // email : user
            users.put(split[7], new Account(split));
        }
    }

    public HashMap<String, Account> getUsers() {
        return users;
    }

    boolean addAccount(String[] data) {
        Account newUser = new Account(data);

        // write user to CSV file
        File outFile = null;
        try {
            outFile = new File(this.getClass().getResource("/Users.csv").toURI());
        } catch (URISyntaxException e) {
            return false;
            //throw new RuntimeException(e);
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outFile.getPath(), true));
        } catch (IOException e) {
            return false;
            //throw new RuntimeException(e);
        }

        try {
            writer.write(newUser.toCSV());
            writer.newLine();
        } catch (IOException e) {
            return false;
            //throw new RuntimeException(e);
        } finally {
            try {
                writer.close(); // close the writer to release resources
            } catch (IOException e) {
                // log or handle the exception
            }
        }

        // write user to map
        users.put(data[7], newUser);

        return true; // user added correctly
    }

    boolean hasExistingAccount(String email) {
        return users.containsKey(email);
    }

    public static void main(String [] args) {
        try {
            AccountDatabase db = new AccountDatabase();

            // print current users
            db.getUsers().forEach((email, user) -> {
                System.out.println(user.toCSV());
            });

            // add one user
            String[] data = {"004", "admin", "Julie", "Richards", "01/12/2001", "F", "111-222-3333", "julierichards@example.com", "topsecret", "1234 One Way St", "12345", "Somewhere", "NJ", "USA"};

            boolean add = db.addAccount(data);

            if(add) {
                System.out.println("Successfully added account");
            } else {
                System.out.println("Couldn't add account");
            }

            if(db.hasExistingAccount(data[7])) {
                System.out.println("Found User");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
