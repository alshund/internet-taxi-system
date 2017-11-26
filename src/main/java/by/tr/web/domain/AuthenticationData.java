package by.tr.web.domain;

import java.io.Serializable;

public class AuthenticationData implements Serializable {
    private static final long serialVersionUID = -1184195490707856225L;

    private String email;
    private String password;
    private String role;
    private HashData hashData;

    public AuthenticationData(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public AuthenticationData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthenticationData() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthenticationData that = (AuthenticationData) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return hashData != null ? hashData.equals(that.hashData) : that.hashData == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (hashData != null ? hashData.hashCode() : 0);
        return result;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public HashData getHashData() {
        return hashData;
    }

    public void setHashData(HashData hashData) {
        this.hashData = hashData;
    }
}
