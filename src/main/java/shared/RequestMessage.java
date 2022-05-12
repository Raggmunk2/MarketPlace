package shared;

import java.io.Serializable;
import java.util.HashMap;

public class RequestMessage implements Serializable{
    private int[] priceRange;
    private TypeOfMessage typeOfMessage;
    private TypeOfProduct typeOfProduct;
    private String userName;
    private String password;
    private User user;
    private Condition condition;
    private Order order;
    private boolean acceptOrDecline;

    //LOGGA IN KONSTRUKTOR
    public RequestMessage(TypeOfMessage typeOfMessage, String userName, String password){
        this.typeOfMessage = typeOfMessage;
        this.userName = userName;
        this.password = password;
    }

    public RequestMessage (TypeOfMessage typeOfMessage, Object object){
        this.typeOfMessage = typeOfMessage;
        if(object instanceof User){
            this.user = (User)object;
        }
        else if(object instanceof TypeOfProduct){
            this.typeOfProduct = (TypeOfProduct)object;
        }
       else if(object instanceof int[]){ //TODO vet ej varför denna ger error
            this.priceRange = (int[]) object;
        }
        if(object instanceof Condition){
            this.condition = (Condition)object;
        }
        else if(object instanceof Order){
          this.order = (Order)object;
        }
        else if(object instanceof HashMap) {
            this.acceptOrDecline = ((HashMap<Order, Boolean>) object).get(0);
        }
    }

    public RequestMessage(TypeOfMessage typeOfMessage) {
        this.typeOfMessage = typeOfMessage;
    }

    public TypeOfMessage getTypeOfMessage(){
        return typeOfMessage;
    }
    public String getUserName(){return userName;}
    public String getPassword(){return password;}
    public TypeOfProduct getTypeOfProduct() {
        return typeOfProduct;
    }
    public User getUser() {
        return user;
    }
    public int[] getPriceRange() {
        return priceRange;
    }
    public Condition getCondition() {
        return condition;
    }
    public Order getOrder() {
        return order;
    }

    public boolean getAcceptOrDecline(){
        return acceptOrDecline;
    }
}
