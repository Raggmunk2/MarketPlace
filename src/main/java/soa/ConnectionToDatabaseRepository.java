package soa;

import dataAccessLayer.ProductRepository;
import java.sql.SQLException;
import java.util.ArrayList;


public class ConnectionToDatabaseRepository {
    private ProductRepository productRepository;

    public ConnectionToDatabaseRepository() throws SQLException {
        ProductRepository productRepository = new ProductRepository();

    }

    public ArrayList getAllProducts() throws SQLException {
        return productRepository.getAllProductsFromDB();
    }

}
