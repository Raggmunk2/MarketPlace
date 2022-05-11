package buisnessLogicLayer;

import dataAccessLayer.repositories.OrderRepository;
import dataAccessLayer.repositories.ProductRepository;
import dataAccessLayer.repositories.UserRepository;
import shared.*;

import java.util.ArrayList;

public class ResponseHandler {

    public ResponseMessage handleRequest(RequestMessage request) {
        ResponseMessage response = null;
        ProductRepository productRepository = null;
        UserRepository userRepository = null;
        OrderRepository orderRepository = null;
        boolean success;

        if (request != null) {
            switch(request.getTypeOfMessage()){
                case LOGIN:
                    userRepository = new UserRepository(); //Skickar tillbaka ett user objekt som skapats i accessControlRepository.checkLogin
                    User user = userRepository.checkLogin(request.getUserName(), request.getPassword());
                    if(user.getUserName() == "No user exists"){
                        return new ResponseMessage(TypeOfMessage.ERROR);
                    }
                    else {
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
                case ORDERS:
                   orderRepository = new OrderRepository(new UserRepository());
                    ArrayList<Order> orders = orderRepository.getOrderHistory(request.getUser());
                    return new ResponseMessage(TypeOfMessage.ORDERS, orders);
                case PRODUCTS:
                    productRepository = new ProductRepository();
                    ArrayList<Product> allProducts = productRepository.getAllProducts();
                    return new ResponseMessage(TypeOfMessage.PRODUCTS, allProducts);
                case CREATE_ORDER:
                    orderRepository = new OrderRepository(new UserRepository());
                    success = orderRepository.addProductToOrder(request.getOrder());
                    return new ResponseMessage(TypeOfMessage.CREATE_ORDER, success);
                case NEW_MESSAGES:
                    orderRepository = new OrderRepository(new UserRepository());
                    ArrayList<Order> newOrders = orderRepository.getOrdersToConfirm(request.getUser());
                    return new ResponseMessage(TypeOfMessage.ORDERS, newOrders);
                case ORDER_RESPONSE:
                    productRepository = new ProductRepository();
                    if(request.getAcceptOrDecline()){
                        productRepository.changeProductStatus(request.getOrder().getProductId(), Status.Sold);
                    }
                    else{
                        productRepository.changeProductStatus(request.getOrder().getProductId(), Status.Available);
                    }
                    orderRepository = new OrderRepository(new UserRepository());
                    boolean removeOrderOK = orderRepository.removeOrder(request.getOrder());
                   return new ResponseMessage(TypeOfMessage.ORDER_RESPONSE, removeOrderOK);
            }
        }
        return new ResponseMessage(TypeOfMessage.ERROR);
    }
}
