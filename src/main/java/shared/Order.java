package shared;

import java.io.Serializable;
import java.sql.Timestamp;


public class Order implements Serializable {

    private int orderId;
    private User buyer;
    private User seller;
    private TypeOfProduct typeOfProduct;
    private double price;
    private Condition condition;
    private String productName;
    private Timestamp CreatedAt;
    private int productId;

    public Order(User buyer, Timestamp createdAt, int productId) {
        this.buyer = buyer;
        this.CreatedAt = createdAt;
        this.productId = productId;
    }

    public Order(User buyer, String productName, User seller, TypeOfProduct typeOfProduct, double price, Condition condition) {
        this.buyer=buyer;
        this.seller = seller;
        this.productName=productName;
        this.typeOfProduct=typeOfProduct;
        this.price=price;
        this.condition=condition;
    }

    public int getOrderId() {
        return orderId;
    }

    public User getBuyer() {
        return buyer;
    }

    public Timestamp getCreatedAt() {
        return CreatedAt;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return
                "orderId=" + orderId +
                        ", buyer=" + buyer.getUserName() +
                        ", CreatedAt=" + CreatedAt +
                        ", productId=" + productId +
                        '}';
    }
}
