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
        boolean success;
        ArrayList<Product> products;

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
                    products = searchByType(request.getTypeOfProduct());
                    return new ResponseMessage(TypeOfMessage.SEARCH_BY_TYPE, products);
                case SEARCH_BY_PRICE:
                    products = searchByPrice(request.getPriceRange());
                    return new ResponseMessage(TypeOfMessage.SEARCH_BY_PRICE, products);
                case SEARCH_BY_CONDITION:
                    products = searchByCondition(request.getCondition());
                    return new ResponseMessage(TypeOfMessage.SEARCH_BY_CONDITION, products);
                case GET_PRODUCTS_TO_CONFIRM:
                   products = getProductsToConfirm(request.getUser());
                    return new ResponseMessage(TypeOfMessage.GET_PRODUCTS_TO_CONFIRM, products);
                case CONFIRM_PRODUCT:
                    success = confirmProduct(request.getAcceptOrDecline(), request.getProduct().getId());
                    return new ResponseMessage(TypeOfMessage.GET_PRODUCTS_TO_CONFIRM, success);
                case SUBSCRIBE_TO_TYPE:
                    subscribeToType(request.getInput(), request.getUserName());
                    return new ResponseMessage(TypeOfMessage.SUBSCRIBE_TO_TYPE);
            }
        }
        return new ResponseMessage(TypeOfMessage.ERROR);
    }

    private boolean confirmProduct(boolean acceptedOrDeclined, int productId) {
        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository(new UserRepository());
        boolean success;
        if (acceptedOrDeclined) {
            success = productRepository.changeProductStatus(productId, Status.Sold);
        } else {
            productRepository.changeProductStatus(productId, Status.Available);
            success = orderRepository.removeOrderByProductId(productId);
        }
        return success;
    }

    private void subscribeToType(int typeOfProduct, String username) {
        SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
        subscriptionRepository.addNewSubscription(typeOfProduct, username);
    }

    private ArrayList<Product> getProductsToConfirm(User user) {
        ProductRepository productRepository = new ProductRepository();
        return productRepository.getAllUnavailableProducts(user);
    }

    private ArrayList<Product> searchByCondition(Condition condition) {
        ProductRepository productRepository = new ProductRepository();
        return productRepository.getProductsByCondition(condition);
    }

    private ArrayList<Product> searchByPrice(double[] priceRange) {
        ProductRepository productRepository = new ProductRepository();
        return productRepository.getProductsByPriceRange(priceRange);
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
        UserRepository userRepository = new UserRepository();
        return userRepository.checkLogin(username, password);
    }

    private boolean register(User user) {
        UserRepository userRepository = new UserRepository();
        return userRepository.registerNewUser(user);
    }
}
