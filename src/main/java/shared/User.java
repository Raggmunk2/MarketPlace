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
    private String lastLogIn;

    public User(String username, String password, String firstName, String lastName, Date dateOfBirth, String email, String lastLogIn) {
        this.userName = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.lastLogIn = lastLogIn;
    }

    public String getLastLogIn() {
        return lastLogIn;
    }

    public void setLastLogIn(String lastLogIn) {
        this.lastLogIn = lastLogIn;
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
