package soa;

import dataAccessLayer.repositories.OrderRepository;
import dataAccessLayer.repositories.ProductRepository;
import dataAccessLayer.repositories.UserRepository;
import shared.User;

import java.sql.SQLException;
import java.util.ArrayList;


public class ConnectionToDatabaseRepository {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public ConnectionToDatabaseRepository() throws SQLException {
        productRepository = new ProductRepository();
        UserRepository acr = new UserRepository();
       orderRepository = new OrderRepository();
    }

    public ArrayList getAllProducts() throws SQLException {
        return productRepository.getAllProducts();
    }

    public ArrayList getOrderHistory() {
       return orderRepository.getStringOrderHistory();
    }
}
