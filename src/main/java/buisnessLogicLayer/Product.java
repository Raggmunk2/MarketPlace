package buisnessLogicLayer;
public class Product {
    private String name;
    private String seller; //TODO ska vara User?
    private TypeOfProduct typeOfProductName;
    private double price;
    private int yearOfMaking;
    private Colour colour;
    private Condition condition;
    private Status status;
    private int id;

    public Product(String name, String seller, TypeOfProduct typeOfProductName, double price, int yearOfMaking, Colour colour, Condition condition, Status status) {
        this.name = name;
        this.seller = seller;
        this.typeOfProductName = typeOfProductName;
        this.price = price;
        this.yearOfMaking = yearOfMaking;
        this.colour = colour;
        this.condition = condition;
        this.status = status;
    }

    public Product(String name, String seller, TypeOfProduct productTypeName, double price, int yearOfMaking,Colour colour, String productConditionName, Status status) {
        this.name = name;
        this.seller = seller;
        this.typeOfProductName = typeOfProductName;
        this.price = price;
        this.yearOfMaking = yearOfMaking;
        this.colour = colour;
        this.condition = condition;
        this.status = status;
    }

    public Product(int productId, String productName, String seller, TypeOfProduct productTypeName, double price, int yearOfMaking, Colour colour, Condition productConditionName, Status status) {
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

    public void setTypeOfProductName(TypeOfProduct typeOfProductName) {
        this.typeOfProductName = typeOfProductName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearOfMaking() {
        return yearOfMaking;
    }

    public void setYearOfMaking(int yearOfMaking) {
        this.yearOfMaking = yearOfMaking;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        condition = condition;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
