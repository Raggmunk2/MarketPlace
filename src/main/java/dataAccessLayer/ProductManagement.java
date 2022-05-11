package dataAccessLayer;

import java.net.UnknownHostException;
import java.sql.SQLException;

public class ProductManagement {
    // Har kvar denna men den är typ som productRepository
    ProductRepository productRepository;

    public ProductManagement(ProductRepository productRepository) throws SQLException, ClassNotFoundException, UnknownHostException {
        this.productRepository = new ProductRepository();
    }



    /*public ResultSet getAllProducts() throws SQLException, ClassNotFoundException { // TODO kolla om denna ska vara här eller i buissnesslogic
        String query = "select name, seller, productTypeName, price,YearOfMaking, colour, productConditionName, [status] from Product\n" +
                "RIGHT JOIN ProductCondition ON Product.condition = ProductCondition.productConditionId\n" +
                "LEFT JOIN ProductTypes ON Product.typeOfProduct = ProductTypes.ProductTypeId\n" +
                "where  Product.[status] = 'Available';";
        Statement statement = ConnectionToDB.getConnectionToDB().createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }*/
}