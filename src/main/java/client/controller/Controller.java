package client.controller;

import client.model.Cart;
import client.model.Inbox;
import client.view.UserInterface;
import shared.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    private UserInterface userInterface;
    private User user;
    private client.controller.RequestHandler requestHandler;
    private Cart cart;
    private client.model.Inbox inbox;

    public Controller() {
        this.userInterface = new UserInterface();
        this.requestHandler = new client.controller.RequestHandler("127.0.0.1", 6890);
        mainMenuHandler(userInterface.showMainMenu());
    }

    private void mainMenuHandler(int input) {
        switch (input) {
            case 1:
                boolean loginSuccess;
                do {
                    loginSuccess = loginUser();
                } while (!loginSuccess);
                loggedInMenuHandler();
                break;
            case 2:
                String firstName = userInterface.getFirstName();
                String lastName = userInterface.getLastName();
                Date DoB = userInterface.getDateOfBirth();
                String email = userInterface.getEmail();
                String newUsername = userInterface.getUsername();
                String newPassword = userInterface.getPassword();
                this.user = new User(newUsername, newPassword, firstName, lastName, DoB, email);
                ResponseMessage registerResponse = requestHandler.getRegisterResponse(user);
                Boolean success = registerResponse.getSuccess();
                if (success) {
                    System.out.println("Welcome " + user.getUserName() + " what would you like to do now?");
                    loggedInMenuHandler();
                } else {
                    System.out.println("Sorry we could not add the user, please try again.");
                    mainMenuHandler(userInterface.showMainMenu());
                }
            case 3:
                System.exit(0);
        }
    }

    private void loggedInMenuHandler() {
        cart = new Cart();
        inbox = new Inbox();
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

    private boolean loginUser() {
        String username = userInterface.getUsername();
        String password = userInterface.getPassword();
        ResponseMessage loginResponse = requestHandler.getLoginResponse(username, password);
        if (loginResponse != null) {
            if (loginResponse.getTypeOfMessage() == TypeOfMessage.ERROR) {
                userInterface.printMessage("Something went wrong. Try again.");
                return false;
            }
        }
        this.user = loginResponse.getUser();
        return true;
    }

    private void logoutUser() {
        this.user = null;
        this.cart = null;
        this.inbox = null;
        mainMenuHandler(userInterface.showMainMenu());
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
