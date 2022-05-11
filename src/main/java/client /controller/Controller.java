package client.controller;

import client.model.Cart;
import client.view.UserInterface;
import shared.*;

import java.sql.Date;
import java.util.ArrayList;

public class Controller {

    private UserInterface userInterface;
    private User user;
    private client.controller.RequestHandler requestHandler;
    private Cart cart;

    public Controller() {
        this.userInterface = new UserInterface();
        this.requestHandler = new client.controller.RequestHandler("127.0.0.1", 6890);
        cart = new Cart();
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

    private boolean loginUser() {
        String username = userInterface.getUsername();
        String password = userInterface.getPassword();
        ResponseMessage loginResponse = requestHandler.getLoginResponse(username, password);
        if (loginResponse != null) {
            if (loginResponse.getTypeOfMessage() == TypeOfMessage.ERROR) {
                userInterface.printErrorMessage("Something went wrong. Try again.");
                return false;
            }
        }
        this.user = loginResponse.getUser();
        return true;
    }

    private void loggedInMenuHandler() {
        int input;
        do {
            input = userInterface.showLoggedInMenu();
            ResponseMessage response = null;
            switch (input) {
                case 1:
                    Product newProduct = null;
                    response = requestHandler.getAllProducts();
                    do {
                        newProduct = userInterface.letUserChooseProduct(response.getProducts());
                    } while (newProduct == null);
                    cart.addToCart(newProduct);
                    break;
                case 2:
                    if (cart.size() == 0) userInterface.printErrorMessage("Your cart is empty at the moment");
                    else {
                        userInterface.showResult("------------YOUR CART------------", cart.getAllProductsInCart());
                    }
                    break;
                case 3:
                    response = requestHandler.getAllOrdersResponse(user);
                    userInterface.showResult("------------YOUR ORDERS------------", response.getOrders());
                    break;
                case 4:
                    ArrayList<Product> productsInCart = this.cart.getAllProductsInCart();
                    response = requestHandler.createOrdersFromCart(productsInCart, this.user);
                    if (response.getSuccess()) {
                        System.out.println("worked!");
                    } else {
                        System.out.println("nope... did not work");
                    }
            cart.resetCart();
            break;

            //TODO handle how to create order
            //The seller should accept or decline the order
            //boolean is returned if seller accepted
            //product should change status
            case 6:
                response = requestHandler.getAllProducts();
                if (response.getProducts().size() == 0) userInterface.printErrorMessage("No result");
                else {
                    userInterface.showResult("------------YOUR RESULTS------------", response.getProducts());
                }
                break;
            case 7:
                response = requestHandler.getSearchByProductResponse(userInterface.getProductType());
                if (response.getProducts().size() == 0) userInterface.printErrorMessage("No result");
                else {
                    userInterface.showResult("------------YOUR RESULTS------------", response.getProducts());
                }
                break;
            case 8:
                int[] range = userInterface.getPriceRange();
                if (range == null) break;
                response = requestHandler.getSearchByPriceResponse(range);
                if (response.getProducts().size() == 0) userInterface.printErrorMessage("No result");
                else {
                    userInterface.showResult("------------YOUR RESULTS------------", response.getProducts());
                }
                break;
            case 9:
                Condition condition = userInterface.getCondition();
                response = requestHandler.getSearchByCondition(condition);
                if (response.getProducts().size() == 0) userInterface.printErrorMessage("No result");
                else {
                    userInterface.showResult("------------YOUR RESULTS------------", response.getProducts());
                }
                break;
            case 12:
                this.user = null;
                this.cart = null;
                mainMenuHandler(userInterface.showMainMenu());
                break;
            default:
                userInterface.printErrorMessage("No valid input, try again");
        }
    } while(input !=9);
}
}
