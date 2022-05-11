package soa;

import dataAccessLayer.AccessControlRepository;
import dataAccessLayer.OrderHistoryRepository;
import dataAccessLayer.ProductRepository;
import shared.User;

import java.sql.SQLException;
import java.util.ArrayList;


public class ConnectionToDatabaseRepository {
    private ProductRepository productRepository;
    private OrderHistoryRepository orderHistoryRepository;

    public ConnectionToDatabaseRepository() throws SQLException {
        productRepository = new ProductRepository();
        orderHistoryRepository = new OrderHistoryRepository(new User("Linn","hejhej", "Linn", "Borgström", null,"hej@hej.com")); //TODO change user to get the in logged user

    }

    public ArrayList getAllProducts() throws SQLException {
        return productRepository.getAllProductsFromDB();
    }

    public ArrayList getOrderHistory() {
        return orderHistoryRepository.getStringOrderHistory();
    }
}
