package by.tr.web.domain;

import java.io.Serializable;

public class HashData implements Serializable {
    private static final long serialVersionUID = -4507023887181315641L;

    private String passwordHash;
    private String salt;

    public HashData(String passwordHash, String salt) {
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public HashData() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashData hashData = (HashData) o;

        if (passwordHash != null ? !passwordHash.equals(hashData.passwordHash) : hashData.passwordHash != null)
            return false;
        return salt != null ? salt.equals(hashData.salt) : hashData.salt == null;
    }

    @Override
    public int hashCode() {
        int result = passwordHash != null ? passwordHash.hashCode() : 0;
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        return result;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
