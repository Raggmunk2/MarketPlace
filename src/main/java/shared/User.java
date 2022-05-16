package shared;

import java.io.Serializable;
import java.sql.*;

public class User implements Serializable {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;

    public User(String username, String password, String firstName, String lastName, Date dateOfBirth, String email) {
        this.userName = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return email;
    }
}
