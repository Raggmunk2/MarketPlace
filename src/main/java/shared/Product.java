package shared;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String seller; //TODO ska vara User?
    private TypeOfProduct typeOfProductName;
    private double price;
    private int yearOfMaking;
    private String colour;
    private Condition condition;
    private Status status;
    private int id;

    public Product(int productId, String productName, String seller, TypeOfProduct productTypeName, double price, int yearOfMaking, String colour, Condition productConditionName, Status status) {
        this.name = productName;
        this.seller = seller;
        this.typeOfProductName = productTypeName;
        this.price = price;
        this.yearOfMaking = yearOfMaking;
        this.colour = colour;
        this.condition = productConditionName;
        this.status = status;
        this.id = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public TypeOfProduct getTypeOfProductName() {
        return typeOfProductName;
    }


    public double getPrice() {
        return price;
    }

    public int getYearOfMaking() {
        return yearOfMaking;
    }

    public void setYearOfMaking(int yearOfMaking) {
        this.yearOfMaking = yearOfMaking;
    }

    public String getColour() {
        return colour;
    }

    public Condition getCondition() {
        return condition;
    }

    public int getConditionAsInt(Condition condition){
        return Condition.getConditionAsInt(condition);
    }

    public int getTypeAsInt(TypeOfProduct typeOfProduct){
        return TypeOfProduct.getTypeAsInt(typeOfProduct);
    }

    public void setCondition(Condition condition) {
        condition = condition;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID= " + id + '\'' +
                "name='" + name + '\'' +
                ", seller='" + seller + '\'' +
                ", type='" + typeOfProductName.toString() + '\'' +
                ", price=" + price +
                ", yearOfMaking=" + yearOfMaking +
                ", colour=" + colour +
                ", condition=" + condition.toString() +
                ", status=" + status +
                '}';
    }
}
