package by.tr.epam.domain;

import java.io.Serializable;

public class Rider implements Serializable {
    private static final long serialVersionUID = -1693796269287420231L;

    private AuthenticationData authenticationData;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Rider(AuthenticationData authenticationData, String firstName, String lastName, String phoneNumber) {
        this.authenticationData = authenticationData;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rider rider = (Rider) o;

        if (authenticationData != null ? !authenticationData.equals(rider.authenticationData) : rider.authenticationData != null)
            return false;
        if (firstName != null ? !firstName.equals(rider.firstName) : rider.firstName != null) return false;
        if (lastName != null ? !lastName.equals(rider.lastName) : rider.lastName != null) return false;
        return phoneNumber != null ? phoneNumber.equals(rider.phoneNumber) : rider.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = authenticationData != null ? authenticationData.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    public AuthenticationData getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(AuthenticationData authenticationData) {
        this.authenticationData = authenticationData;
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
}
