package client.controller;

import client.model.Cart;
import client.model.ProductInbox;
import client.view.UserInterface;
import shared.*;

import java.util.ArrayList;
import java.util.HashMap;

public class LoggedInController {

    private User user;
    private UserInterface userInterface;
    private client.controller.RequestHandler requestHandler;
    private Cart cart;
    private ProductInbox productInbox;

    public LoggedInController(User user, UserInterface userInterface, RequestHandler requestHandler) {
        this.user = user;
        this.userInterface = userInterface;
        this.requestHandler = requestHandler;
        cart = new Cart();
        productInbox = new ProductInbox();
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
                case 4:
                    createOrder();
                    break;
                case 6:
                    printAllProducts();
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
                    handleProductInbox();
                    break;
            }
        } while (input != 12);
        userInterface.printMessage("Okay, bye!");
        logoutUser();
    }


    private void logoutUser() {
        this.user = null;
        this.cart = null;
        this.productInbox = null;
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

    private void printAllProducts(){
        ResponseMessage response = requestHandler.getAllProducts();
        if (response.getProducts().size() == 0) userInterface.printMessage("No result");
        else {
           userInterface.showResult("YOUR RESULT)", response.getProducts());
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

    private void handleProductInbox() {
        //SKA HÄMTA IN EN LISTA PÅ PRODUKTER SOM NÅGON VILL KÖPA
        ResponseMessage response = requestHandler.getAllProductsToConfirm(this.user);
        productInbox.update(response.getProducts());
        Product product = (Product)userInterface.letUserChooseFromList(productInbox.getProductsToConfirm());
        Boolean acceptOrDecline = userInterface.getBoolean("Accept or decline? (True/False)");
        ResponseMessage responseMessage = requestHandler.confirmProduct(product, acceptOrDecline);
        userInterface.printMessage("Confirmed offer: " + response.getSuccess());
    }
}
