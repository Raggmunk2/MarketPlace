package shared;

import java.io.Serializable;

public class ReturnMessage implements Serializable {

    private String typeOfMessage;
    private User user;

    public ReturnMessage(String typeOfMessage, User user){
        this.typeOfMessage = typeOfMessage;
        this.user = user;
    }

    public String getTypeOfMessage() {return typeOfMessage;}

    public User getUser() {return user;}
}
