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
                    User user = loginUser(request.getUserName(), request.getPassword());
                    if (user.getUserName() == "No user exists") {
                        return new ResponseMessage(TypeOfMessage.ERROR);
                    } else {
                        return new ResponseMessage(TypeOfMessage.LOGIN, user);//Response som skickas tillbaka till klienten
                    }
                case REGISTER:
                    success = register(request.getUser());
                    return new ResponseMessage(TypeOfMessage.REGISTER, success);
                case CREATE_ORDER:
                    success = createOrder(request.getProductsInCart(), request.getUser());
                    return new ResponseMessage(TypeOfMessage.CREATE_ORDER, success);
                case CREATE_PRODUCT_FOR_SELLING:
                    success = sellProduct(request.getProduct());
                    return new ResponseMessage(TypeOfMessage.CREATE_PRODUCT_FOR_SELLING, success);
                case GET_ALL_PRODUCTS:
                    ArrayList<Product> allProducts = getAllProducts();
                    return new ResponseMessage(TypeOfMessage.GET_ALL_PRODUCTS, allProducts);
                case SEARCH_BY_TYPE:
                    ArrayList<Product> products = searchByType(request.getTypeOfProduct());
                    return new ResponseMessage(TypeOfMessage.SEARCH_BY_TYPE, products);
                case SEARCH_BY_PRICE:
                    productRepository = new ProductRepository();
                    ArrayList<Product> products2 = productRepository.getProductsByPriceRange(request.getPriceRange());
                    return new ResponseMessage(TypeOfMessage.SEARCH_BY_PRICE, products2);
                case SEARCH_BY_CONDITION:
                    productRepository = new ProductRepository();
                    ArrayList<Product> products3 = productRepository.getProductsByCondition(request.getCondition());
                    return new ResponseMessage(TypeOfMessage.SEARCH_BY_CONDITION, products3);

                case PRODUCTS_TO_CONFIRM:
                    productRepository = new ProductRepository();
                    ArrayList<Product> productsToConfirm = productRepository.getAllUnavailableProducts(request.getUser());
                    return new ResponseMessage(TypeOfMessage.PRODUCTS_TO_CONFIRM, productsToConfirm);
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

                case SUBSCRIBE_TO_TYPE:
                    subscriptionRepository = new SubscriptionRepository();
                    subscriptionRepository.addNewSubscription(request.getInput(), request.getUserName());
                    return new ResponseMessage(TypeOfMessage.SUBSCRIBE_TO_TYPE);
            }
        }
        return new ResponseMessage(TypeOfMessage.ERROR);
    }

    private ArrayList<Product> searchByType(TypeOfProduct typeOfProduct) {
        ProductRepository productRepository = new ProductRepository();
        return productRepository.getProductsByTypeOfProduct(typeOfProduct);
    }

    private ArrayList<Product> getAllProducts() {
        ProductRepository productRepository = new ProductRepository();
        return productRepository.getAllProducts();
    }

    private boolean sellProduct(Product product) {
        ProductRepository productRepository = new ProductRepository();
        return productRepository.addProductToDatabase(product);
    }

    private boolean createOrder(ArrayList<Product> productsInCart, User user) {
        OrderRepository orderRepository = new OrderRepository(new UserRepository());
        boolean orderCreated = false;
        for (Product p : productsInCart) {
            Order order = new Order(user, Timestamp.valueOf(LocalDateTime.now()), p.getId());
            orderCreated = orderRepository.addProductToOrder(order);
        }
        return orderCreated;
    }

    private User loginUser(String username, String password) {
        UserRepository userRepository = new UserRepository(); //Skickar tillbaka ett user objekt som skapats i accessControlRepository.checkLogin
        return userRepository.checkLogin(username, password);
    }

    private boolean register(User user) {
        UserRepository userRepository = new UserRepository();
        return userRepository.registerNewUser(user);
    }
}
