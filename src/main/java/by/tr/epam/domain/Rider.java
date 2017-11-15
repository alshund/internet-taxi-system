package by.tr.epam.domain;

import java.io.Serializable;

public class Rider implements Serializable {
    private static final long serialVersionUID = -1693796269287420231L;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;

    public Rider(String firstName, String lastName, String phoneNumber, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rider rider = (Rider) o;

        if (firstName != null ? !firstName.equals(rider.firstName) : rider.firstName != null) return false;
        if (lastName != null ? !lastName.equals(rider.lastName) : rider.lastName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(rider.phoneNumber) : rider.phoneNumber != null) return false;
        if (email != null ? !email.equals(rider.email) : rider.email != null) return false;
        return password != null ? password.equals(rider.password) : rider.password == null;
    }

    @Override
    public int hashCode() {

        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
}
