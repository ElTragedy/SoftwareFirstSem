package backend;

import java.util.Objects;

import jakarta.xml.bind.annotation.*;



@XmlEnum
enum Access {
    @XmlEnumValue("guest")
    guest,
    @XmlEnumValue("clerk")
    clerk,
    @XmlEnumValue("admin")
    admin
}

@XmlRootElement (name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {
    @XmlAttribute
    private Access access;
    @XmlAttribute
    private String id;
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private String DOB;
    @XmlElement
    private String sex;
    @XmlElement
    private String phoneNumber;
    @XmlElement
    private String email;
    @XmlElement
    private String password;
    @XmlElement
    private String address;
    @XmlElement
    private String zipcode;
    @XmlElement
    private String city;
    @XmlElement 
    private String state;
    @XmlElement
    private String country;

    public Account(){
    }

    /**
     * Returns the access level of the Account.
     *
     * @return a string representation of the access level.
     */
    public String getAccess() {
        return access.name();
    }

    /**
     * Sets the access level of the Account.
     *
     * @param access the new access level to set.
     */
    public void setAccess(String access) {
        this.access = Access.valueOf(access);
    }

    /**
     * Constructs an Account object with the given parameters.
     *
     * @param id the ID of the account.
     * @param firstName the first name of the account holder.
     * @param lastName the last name of the account holder.
     * @param DOB the date of birth of the account holder.
     * @param sex the sex of the account holder.
     * @param phoneNumber the phone number of the account holder.
     * @param email the email address of the account holder.
     * @param password the password of the account.
     * @param address the address of the account holder.
     * @param zipcode the zipcode of the account holder's address.
     * @param city the city of the account holder's address.
     * @param state the state of the account holder's address.
     * @param country the country of the account holder's address.
     */
    public Account(String id, String firstName, String lastName, String DOB, String sex, String phoneNumber, String email,
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

    /**
     * Constructs an Account object with the given parameters.
     *
     * @param id the ID of the account.
     * @param firstName the first name of the account holder.
     * @param lastName the last name of the account holder.
     * @param DOB the date of birth of the account holder.
     * @param sex the sex of the account holder.
     * @param phoneNumber the phone number of the account holder.
     * @param email the email address of the account holder.
     * @param password the password of the account.
     * @param address the address of the account holder.
     * @param zipcode the zipcode of the account holder's address.
     * @param city the city of the account holder's address.
     * @param state the state of the account holder's address.
     * @param country the country of the account holder's address.
     * @param access the access level of the account.
     */
    public Account(String id, String firstName, String lastName, String DOB, String sex, String phoneNumber, String email,
                   String password, String address, String zipcode, String city, String state, String country, String access) {
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
        this.access = Access.valueOf(access);
    }

    /**
     * Constructs an Account object with the given array of data.
     *
     * @param data an array containing the account data.
     *             The data must be in the following order:
     *             id, access, firstName, lastName, DOB, sex, phoneNumber, email, password,
     *             address, zipcode, city, state, country
     */
    public Account(String [] data) {
        this.id = data[0];
        this.access = Access.valueOf(data[1]);
        this.firstName = data[2];
        this.lastName = data[3];
        this.DOB = data[4];
        this.sex = data[5];
        this.phoneNumber = data[6];
        this.email = data[7];
        this.password = data[8];
        this.address = data[9];
        this.zipcode = data[10];
        this.city = data[11];
        this.state = data[12];
        this.country = data[13];
    }

    /**
     * Returns the ID of this account.
     *
     * @return the ID of this account.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of this account.
     *
     * @param id the ID of this account.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the first name of the user associated with this account.
     *
     * @return the first name of the user associated with this account.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user associated with this account.
     *
     * @param firstName the first name of the user associated with this account.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the user associated with this account.
     *
     * @return the last name of the user associated with this account.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user associated with this account.
     *
     * @param lastName the last name of the user associated with this account.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the date of birth of the user associated with this account.
     *
     * @return the date of birth of the user associated with this account.
     */
    public String getDOB() {
        return DOB;
    }

    /**
     * Sets the date of birth of the user associated with this account.
     *
     * @param DOB the date of birth of the user associated with this account.
     */
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    /**
     * Returns the sex of the user associated with this account.
     *
     * @return the sex of the user associated with this account.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the sex of the user associated with this account.
     *
     * @param sex the sex of the user associated with this account.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Returns the phone number of the user associated with this account.
     *
     * @return the phone number of the user associated with this account.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user associated with this account.
     *
     * @param phoneNumber the phone number of the user associated with this account.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the email of the user associated with this account.
     *
     * @return the email of the user associated with this account.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user associated with this account.
     *
     * @param email the email of the user associated with this account.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the user associated with this account.
     *
     * @return the password of the user associated with this account.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user associated with this account.
     *
     * @param password the password of the user associated with this account.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the address of the user associated with this account.
     *
     * @return the address of the user associated with this account.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the user associated with this account.
     *
     * @param address the address of the user associated with this account.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the zipcode for this account.
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Sets the zipcode for this account.
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Returns the city for this account.
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city for this account.
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the state for this account.
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state for this account.
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the country for this account.
     * @return the country
     */
    public String getCountry() { return country; }

    /**
     * Sets the country for this account.
     * @param country the country to set
     */
    public void setCountry(String country) { this.country = country; }

    /**
     * Determines whether or not this object is equal to another object.
     * Two objects are considered equal if they have the same ID, first name, last name, DOB, sex, phone number, email, password, address, zipcode, city, and state.
     * @param object the object to compare this object to
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Account user = (Account) object;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getDOB(), user.getDOB()) && Objects.equals(getSex(), user.getSex()) && Objects.equals(getPhoneNumber(), user.getPhoneNumber()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getZipcode(), user.getZipcode()) && Objects.equals(getCity(), user.getCity()) && Objects.equals(getState(), user.getState());
    }

    /**
     * Returns the hash code value for this account.
     * The hash code value is based on the email field.
     * @return the hash code value for this account
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Returns a string representation of this account.
     * The string representation includes the values of all fields for this account.
     * @return a string representation of this account
     */
    public String toString() {
        return "Account{" +
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

    /**
     * Returns a comma-separated string representation of the Account object
     * in the format: id,access,firstName,lastName,DOB,sex,phoneNumber,email,password,address,zipcode,city,state,country
     *
     * @return a comma-separated string representation of the Account object
     */
    public String toCSV() {
        return this.id + ',' +
                this.access.toString() + ',' +
                this.firstName + ',' +
                this.lastName + ',' +
                this.DOB + ',' +
                this.sex + ',' +
                this.phoneNumber + ',' +
                this.email + ',' +
                this.password + ',' +
                this.address + ',' +
                this.zipcode + ',' +
                this.city + ',' +
                this.state + ',' +
                this.country;
    }

}
