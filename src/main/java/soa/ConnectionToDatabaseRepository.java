package soa;


import dataAccessLayer.repositories.OrderRepository;
import dataAccessLayer.repositories.ProductRepository;
import dataAccessLayer.repositories.UserRepository;


import java.sql.SQLException;
import java.util.ArrayList;


public class ConnectionToDatabaseRepository {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public ConnectionToDatabaseRepository() throws SQLException {
        productRepository = new ProductRepository();

        orderHistoryRepository = new OrderHistoryRepository(new User("Linn","hejhej", "Linn", "Borgström", null,"hej@hej.com")); //TODO change user to get the in logged user


    }

    public ArrayList getAllProducts() throws SQLException {
        return productRepository.getAllProducts();
    }

    public ArrayList getOrderHistory() {
       return orderRepository.getStringOrderHistory();
        return null
    }
}
