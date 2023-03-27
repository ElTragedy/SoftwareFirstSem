package main.java.backend;

enum Access {
    guest, clerk, admin
}

public class Account {
    String username;
    String password;
    Access power;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Access getPower() {
        return power;
    }

    public void setPower(Access power) {
        this.power = power;
    }
}

class Main {
    public static void main(String[] args) {
        Account temp = new Account();
        temp.setUsername("bobstone123");
        temp.setPassword("amongUs2856");
        temp.setPower(Access.guest);

        System.out.println("Username: " + temp.getUsername());
        System.out.println("Password: " + temp.getPassword());
        System.out.println("Access Level " +  temp.getPower());
    }
}



