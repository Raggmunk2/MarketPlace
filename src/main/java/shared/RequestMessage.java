package shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestMessage implements Serializable{
    private double[] priceRange;
    private TypeOfMessage typeOfMessage;
    private TypeOfProduct typeOfProduct;
    private String userName;
    private String password;
    private User user;
    private Condition condition;
    private Order order;
    private boolean acceptOrDecline;
    private  int input;
    private Product product;
    private ArrayList<Product> productsInCart;

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
       else if(object instanceof double[]){
            this.priceRange = (double[]) object;
        }
        if(object instanceof Condition){
            this.condition = (Condition)object;
        }
        else if(object instanceof Order){
          this.order = (Order)object;
        }
    }

    public RequestMessage(TypeOfMessage typeOfMessage) {
        this.typeOfMessage = typeOfMessage;
    }

    public RequestMessage(TypeOfMessage typeOfMessage, int input, String userName){
        this.typeOfMessage = typeOfMessage;
        this.input = input;
        this.userName = userName;
    }

    public RequestMessage(TypeOfMessage typeOfMessage, Product product, boolean acceptOrDecline) {
        this.typeOfMessage = typeOfMessage;
        this.product = product;
        this.acceptOrDecline = acceptOrDecline;
    }

    public RequestMessage(TypeOfMessage createOrder, ArrayList<Product> productsInCart, User user) {
        this.typeOfMessage = createOrder;
        this.productsInCart = productsInCart;
        this.user = user;
     }

    public Product getProduct() {
        return product;
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
    public double[] getPriceRange() {
        return priceRange;
    }
    public Condition getCondition() {
        return condition;
    }
    public Order getOrder() {
        return order;
    }
    public int getInput(){
        return input;
    }

    public boolean getAcceptOrDecline(){
        return acceptOrDecline;
    }
    public ArrayList<Product> getProductsInCart() {
        return productsInCart;
    }
}
