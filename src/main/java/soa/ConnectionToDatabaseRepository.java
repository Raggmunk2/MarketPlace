package soa;


import dataAccessLayer.repositories.OrderRepository;
import dataAccessLayer.repositories.ProductRepository;
import dataAccessLayer.repositories.UserRepository;
import java.util.ArrayList;


public class ConnectionToDatabaseRepository {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private UserRepository ur;

    public ConnectionToDatabaseRepository() {
        productRepository = new ProductRepository();
        ur = new UserRepository();
        orderRepository = new OrderRepository(ur);

    }

    public ArrayList getAllProducts(){
        return productRepository.getAllProducts();
    }

    public ArrayList getOrderHistory() {
       return orderRepository.getOrderHistory(ur.getUser("Linn"));//TODO change user to get the in logged user
    }
}
