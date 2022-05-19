package dataAccessLayer.repositories;

import shared.User;

import java.util.ArrayList;


public class SoaRepositoryConnector {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private UserRepository ur;

    /**
     * Constructor to connect to the database
     */
    public SoaRepositoryConnector() {
        productRepository = new ProductRepository();
        ur = new UserRepository();
        orderRepository = new OrderRepository(ur);
    }

    /**
     * Makes a request to the orderRepository to get the orders
     * @param username username to search the orders for
     * @return
     */
    public ArrayList getOrderHistoryByName(String username) {
        System.out.println("from ctDBr: " +username);
        User user = ur.getUser(username);
        return orderRepository.getOrderHistory(user);
    }
}
