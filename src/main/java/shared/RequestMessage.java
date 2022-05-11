package shared;

import java.io.Serializable;
import java.sql.Date;

public class RequestMessage implements Serializable{
    private String typeOfMessage;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private Date DoB;
    private Object object;

    /*public Message(String typeOfMessage, Object object){
        this.typeOfMessage = typeOfMessage;
        this.object = object;
    }*/
    public RequestMessage(String typeOfMessage, String userName, String password){
        this.typeOfMessage = typeOfMessage;
        this.userName = userName;
        this.password = password;
    }

    public RequestMessage(String typeOfMessage, String firstName, String lastName, Date doB, String email, String userName, String password) {
        this.typeOfMessage = typeOfMessage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        DoB = doB;
    }


    public String getTypeOfMessage(){return typeOfMessage;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getEmail(){return email;}
    public String getUserName(){return userName;}
    public String getPassword(){return password;}
    public Date getDoB(){return DoB;}
    public Object getObject(){return object;}
}
