package client.controller;

import client.model.Cart;
import client.model.Inbox;
import client.view.UserInterface;
import shared.*;

import java.util.ArrayList;
import java.util.HashMap;

public class LoggedInController {

    private User user;
    private UserInterface userInterface;
    private client.controller.RequestHandler requestHandler;
    private Cart cart;
    private client.model.Inbox inbox;

    public LoggedInController(User user, UserInterface userInterface, RequestHandler requestHandler) {
        this.user = user;
        this.userInterface = userInterface;
        this.requestHandler = requestHandler;
        cart = new Cart();
        inbox = new Inbox();
        loggedInMenuHandler();
    }

    private void loggedInMenuHandler() {
        int input;
        do {

            input = userInterface.showLoggedInMenu();
            switch (input) {
                case 1:
                    addProductToCart();
                    break;
                case 2:
                    if (cart.size() == 0) userInterface.printMessage("Your cart is empty at the moment");
                    else {
                        userInterface.showResult("------------YOUR CART------------", cart.getAllProductsInCart());
                    }
                    break;
                case 3:
                    getAllOrders();
                    break;
                case 4:
                    createOrder();
                    break;
                case 5:
                    createProductToSell();
                    break;
                case 6:
                    getAllProducts();
                    break;
                case 7:
                    searchByProductType();
                    break;
                case 8:
                    searchByPriceRange();
                    break;
                case 9:
                    searchByCondition();
                    break;
                case 10:
                    handleInbox();
                    break;
            }
        } while (input != 12);
        userInterface.printMessage("Okay, bye!");
        logoutUser();
    }


    private void logoutUser() {
        this.user = null;
        this.cart = null;
        this.inbox = null;
        System.exit(1);
    }

    private void addProductToCart() {
        Product newProduct = null;
        ArrayList<Product> products = getAllProducts();
        if (products != null) {
            do {
                newProduct = (Product) userInterface.letUserChooseFromList(products);
            } while (newProduct == null);
            cart.addToCart(newProduct);
        }
    }

    private ArrayList<Product> getAllProducts(){
        ResponseMessage response = requestHandler.getAllProducts();
        if (response.getProducts().size() == 0) userInterface.printMessage("No result");
        else {
            return response.getProducts();
        }
        return null;
    }

    private void getAllOrders() {
        ResponseMessage response = requestHandler.getAllOrdersResponse(user);
        userInterface.showResult("------------YOUR ORDERS------------", response.getOrders());
    }

    private void createProductToSell() {
        String productName = userInterface.chooseProductName();
        double price = userInterface.getProductPrice();
        Condition condition = userInterface.getCondition();
        TypeOfProduct typeOfProduct = userInterface.getProductType();
        Colour colour = userInterface.getColor();
        int yearOfMaking = userInterface.chooseYearOfMaking();
        Product product = new Product(00, productName, user.getUserName(), typeOfProduct, price,
                yearOfMaking, colour, condition, Status.Available);

        ResponseMessage response = requestHandler.createProductToSell(product);
        if(response.getSuccess()){
            userInterface.printMessage("Your product was successfully added for sale!");
        }else{
            userInterface.printMessage("Product could not be created for sale");
        }
    }



    private void createOrder() {
        ResponseMessage response = requestHandler.createOrdersFromCart(this.cart.getAllProductsInCart(), this.user);
        if (response.getSuccess()) {
            userInterface.printMessage("Order is created");
        } else {
            userInterface.printMessage("Order could not be created");
        }
        cart.resetCart();
    }

    private void searchByCondition() {
        Condition condition = userInterface.getCondition();
        ResponseMessage response = requestHandler.getSearchByCondition(condition);
        if (response.getProducts().size() == 0) userInterface.printMessage("No result");
        else {
            userInterface.showResult("------------YOUR RESULTS------------", response.getProducts());
        }
    }

    private void searchByPriceRange() {
        int[] range = userInterface.getPriceRange();
        ResponseMessage response = requestHandler.getSearchByPriceResponse(range);
        if (response.getProducts().size() == 0) userInterface.printMessage("No result");
        else {
            userInterface.showResult("------------YOUR RESULTS------------", response.getProducts());
        }
    }

    private void searchByProductType() {
        ResponseMessage response = requestHandler.getSearchByProductResponse(userInterface.getProductType());
        if (response.getProducts().size() == 0) userInterface.printMessage("No result");
        else {
            userInterface.showResult("------------YOUR RESULTS------------", response.getProducts());
        }
    }

    private void handleInbox() {
        updateInbox();
        HashMap<Order, Boolean> result = confirmOrder();
        ResponseMessage response = requestHandler.confirmOrder(result);
        userInterface.printMessage("Confirmed offer: " +response.getSuccess());
    }

    private void updateInbox() {
        ResponseMessage response = requestHandler.getOrdersToConfirm();
        inbox.updateOrdersToConfirm(response.getOrders());
    }

    private HashMap<Order, Boolean> confirmOrder(){
        ArrayList<Order> orders = inbox.getOrdersToConfirm();
        Order order = (Order) userInterface.letUserChooseFromList(orders);
        boolean responseToOffer = userInterface.getBoolean("Accept or decline? (True/False)");
        HashMap<Order, Boolean> result = new HashMap<>();
        result.put(order, responseToOffer);
        return result;
    }
}
