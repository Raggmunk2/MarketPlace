package shared;
import buisnessLogicLayer.Inbox;

import java.sql.*;
import java.util.ArrayList;

public class User { //TODO beh�ver n�got mer?

    private String userName;
    private String password;
    private String firstName;
    private Date dateOfBirth;
    private String lastName;
    private String eMail;
    private Date lastLogin;
    private boolean isOnline;
    private ArrayList<Inbox> inbox = new ArrayList<>();


    public User(String userName, String password, String firstName, String lastName, Date dateOfBirth, String eMail) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.eMail = eMail;
    }

    public User(String userName, String password, String firstName, String lastName, Date dateOfBirth, String eMail, Date lastLogin) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.eMail = eMail;
        this.lastLogin = lastLogin;
    }


    public User(String newUserName, String newUserPassword, String newUserFirstname, String newUserLastName, Date newUserdateOfBirth, String newUserEmail, Date lastLogin, boolean isOnline) {
        this.userName = newUserName;
        this.password = newUserPassword;
        this.firstName = newUserFirstname;
        this.lastName = newUserLastName;
        this.dateOfBirth = newUserdateOfBirth;
        this.eMail = newUserEmail;
        this.lastLogin = lastLogin;
        this.isOnline = isOnline;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }


}
