package backend;

import java.util.Objects;

public class User {
    private enum type {ADMIN, GUEST};
    private String id;
    private String firstName;
    private String lastName;
    private String DOB;
    private String sex;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
    private String zipcode;
    private String city;
    private String state;
    private String country;

    public User(String id, String firstName, String lastName, String DOB, String sex, String phoneNumber, String email,
                String password, String address, String zipcode, String city, String state, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public void addUser() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        User user = (User) object;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getDOB(), user.getDOB()) && Objects.equals(getSex(), user.getSex()) && Objects.equals(getPhoneNumber(), user.getPhoneNumber()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getZipcode(), user.getZipcode()) && Objects.equals(getCity(), user.getCity()) && Objects.equals(getState(), user.getState());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getFirstName(), getLastName(), getDOB(), getSex(), getPhoneNumber(), getEmail(), getPassword(), getAddress(), getZipcode(), getCity(), getState());
    }

    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", DOB='" + DOB + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

}
