package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseMessage implements Serializable {

    private TypeOfMessage typeOfMessage;
    private User user;
    private ArrayList<Product> products;
    private Boolean success;
    private ArrayList<Order> orders;

    public ResponseMessage(TypeOfMessage type) {
        this.typeOfMessage = type;
    }

    public ResponseMessage(TypeOfMessage typeOfMessage, Object object){
        this.typeOfMessage = typeOfMessage;
        if(object instanceof User){
            this.user = (User)object;
        }
        else if(object instanceof ArrayList) {
            if(typeOfMessage == TypeOfMessage.ORDERS){
               this.orders = (ArrayList<Order>) object;
            }
            else {
                this.products = (ArrayList<Product>) object;
            }
        }
        else if(object instanceof Boolean){
            this.success = (Boolean) object;
        }
    }

    public TypeOfMessage getTypeOfMessage() {return typeOfMessage;}

    public User getUser() {return user;}

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Boolean getSuccess() {
        return success;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
