package client.controller;

import client.model.Cart;
import client.model.ProductInbox;
import client.view.UserInterface;
import shared.*;

import java.util.ArrayList;

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
                        boolean checkoutCart = userInterface.getBoolean("Do you want to checkout your cart? (true/false)");

                        if(checkoutCart){
                            ResponseMessage response = requestHandler.createOrdersFromCart(cart.getAllProductsInCart(), user);
                            if(response.getSuccess()){
                                System.out.println("Your order has been submitted");
                            }else{
                                System.out.println("Something went wrong");
                            }
                            cart.resetCart();
                        }
                    }
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

    private void searchByCondition() {
        Condition condition = userInterface.getCondition();
        ResponseMessage response = requestHandler.getSearchByCondition(condition);
        if (response.getProducts().size() == 0) userInterface.printMessage("No result");
        else {
            userInterface.showResult("------------YOUR RESULTS------------", response.getProducts());
        }
    }

    private void searchByPriceRange() {
        double[] range = userInterface.getPriceRange();
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
        ResponseMessage response = requestHandler.getAllProductsToConfirm(this.user);
        if(response.getProducts().size() == 0){
            userInterface.printMessage("You have no new messages");
        }
        else {
            productInbox.update(response.getProducts());
            Product product = (Product) userInterface.letUserChooseFromList(productInbox.getProductsToConfirm());
            Boolean acceptOrDecline = userInterface.getBoolean("Accept or decline? (True/False)");
            ResponseMessage responseMessage = requestHandler.confirmProduct(product, acceptOrDecline);
            userInterface.printMessage("Confirmed offer: " + responseMessage.getSuccess());
            productInbox.resetProductInbox();
        }
    }
}
