package soa;

import dataAccessLayer.AccessControlRepository;
import dataAccessLayer.OrderHistoryRepository;
import dataAccessLayer.ProductRepository;
import java.sql.SQLException;
import java.util.ArrayList;


public class ConnectionToDatabaseRepository {
    private ProductRepository productRepository;
    private OrderHistoryRepository orderHistoryRepository;

    public ConnectionToDatabaseRepository() throws SQLException {
        productRepository = new ProductRepository();
        AccessControlRepository acr = new AccessControlRepository();
        orderHistoryRepository = new OrderHistoryRepository(acr.getUser());

    }

    public ArrayList getAllProducts() throws SQLException {
        return productRepository.getAllProductsFromDB();
    }

    public ArrayList getOrderHistory() {
        return orderHistoryRepository.getStringOrderHistory();
    }
}
