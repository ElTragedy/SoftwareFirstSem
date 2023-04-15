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

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

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
        Account user = (Account) object;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getDOB(), user.getDOB()) && Objects.equals(getSex(), user.getSex()) && Objects.equals(getPhoneNumber(), user.getPhoneNumber()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getZipcode(), user.getZipcode()) && Objects.equals(getCity(), user.getCity()) && Objects.equals(getState(), user.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

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
