package buisnessLogicLayer;

import dataAccessLayer.repositories.OrderRepository;
import dataAccessLayer.repositories.ProductRepository;
import dataAccessLayer.repositories.SubscriptionRepository;
import dataAccessLayer.repositories.UserRepository;
import shared.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ResponseHandler {

    public ResponseMessage handleRequest(RequestMessage request) {
        ResponseMessage response = null;
        ProductRepository productRepository = null;
        UserRepository userRepository = null;
        OrderRepository orderRepository = null;
        SubscriptionRepository subscriptionRepository = null;
        boolean success;

        if (request != null) {
            switch (request.getTypeOfMessage()) {
                case LOGIN:
                    userRepository = new UserRepository();
                    User user = userRepository.checkLogin(request.getUserName(), request.getPassword());
                    if (user.getUserName() == "No user exists") {
                        return new ResponseMessage(TypeOfMessage.ERROR);
                    } else {
                        return new ResponseMessage(TypeOfMessage.LOGIN, user);//Response som skickas tillbaka till klienten
                    }
                case REGISTER:
                    userRepository = new UserRepository();
                    success = userRepository.registerNewUser(request.getUser());
                    return new ResponseMessage(TypeOfMessage.REGISTER, success);
                case SEARCH_BY_TYPE:
                    productRepository = new ProductRepository();
                    ArrayList<Product> products = productRepository.getProductsByTypeOfProduct(request.getTypeOfProduct());
                    return new ResponseMessage(TypeOfMessage.SEARCH_BY_TYPE, products);
                case SEARCH_BY_PRICE:
                    productRepository = new ProductRepository();
                    ArrayList<Product> products2 = productRepository.getProductsByPriceRange(request.getPriceRange());
                    return new ResponseMessage(TypeOfMessage.SEARCH_BY_PRICE, products2);
                case SEARCH_BY_CONDITION:
                    productRepository = new ProductRepository();
                    ArrayList<Product> products3 = productRepository.getProductsByCondition(request.getCondition());
                    return new ResponseMessage(TypeOfMessage.SEARCH_BY_CONDITION, products3);
                case PRODUCTS:
                    productRepository = new ProductRepository();
                    ArrayList<Product> allProducts = productRepository.getAllProducts();
                    return new ResponseMessage(TypeOfMessage.PRODUCTS, allProducts);
                case PRODUCTS_TO_CONFIRM:
                    productRepository = new ProductRepository();
                    ArrayList<Product> productsToConfirm = productRepository.getAllUnavailableProducts(request.getUser());
                    return new ResponseMessage(TypeOfMessage.PRODUCTS_TO_CONFIRM, productsToConfirm);
                case CREATE_PRODUCT_FOR_SELLING:
                    productRepository = new ProductRepository();
                    success = productRepository.addProductToDatabase(request.getProduct());
                    return new ResponseMessage(TypeOfMessage.CREATE_PRODUCT_FOR_SELLING, success);

                    case CONFIRM_PRODUCT:
                    productRepository = new ProductRepository();
                    orderRepository = new OrderRepository(new UserRepository());
                    boolean ok;
                    if (request.getAcceptOrDecline()) {
                        ok = productRepository.changeProductStatus(request.getProduct().getId(), Status.Sold);
                    } else {
                        productRepository.changeProductStatus(request.getProduct().getId(), Status.Available);
                        ok = orderRepository.removeOrderByProductId(request.getProduct().getId());
                    }
                    return new ResponseMessage(TypeOfMessage.PRODUCTS_TO_CONFIRM, ok);
                case CREATE_ORDER:
                    orderRepository = new OrderRepository(new UserRepository());
                    ArrayList<Product> productsInCart = request.getProductsInCart();
                    boolean orderCreated = false;
                    for (Product p : productsInCart) {
                        Order order = new Order(request.getUser(), Timestamp.valueOf(LocalDateTime.now()), p.getId());
                        orderCreated = orderRepository.addProductToOrder(order);
                    }
                    return new ResponseMessage(TypeOfMessage.CREATE_ORDER, orderCreated);
                case SUBSCRIBE_TO_TYPE:
                    subscriptionRepository = new SubscriptionRepository();
                    subscriptionRepository.addNewSubscription(request.getInput(), request.getUserName());
                    return new ResponseMessage(TypeOfMessage.SUBSCRIBE_TO_TYPE);

                case NOTIFICATION:
                    productRepository = new ProductRepository();
                    success = productRepository.getNotification(request.getUser());
                    System.out.println("ResponeHandler - "+success);
                    System.out.println("ResponeHandler - lastLogin: " + request.getUser().getLastLogIn());
                    System.out.println("ResponeHandler - username: " + request.getUserName());
                    return new ResponseMessage(TypeOfMessage.NOTIFICATION,success);
            }
        }
        return new ResponseMessage(TypeOfMessage.ERROR);
    }
}
