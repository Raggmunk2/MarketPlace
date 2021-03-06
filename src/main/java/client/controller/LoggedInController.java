package client.controller;

import client.model.Cart;
import client.model.ProductInbox;
import client.view.UserInterface;
import shared.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
            int productInboxSize = getProductInboxSize();
            input = userInterface.showLoggedInMenu(productInboxSize);
            switch (input) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    createOrder();
                    break;
                case 3:
                    createProductToSell();
                    break;
                case 4:
                    printAllProducts();
                    break;
                case 5:
                    searchByProductType();
                    break;
                case 6:
                    searchByPriceRange();
                    break;
                case 7:
                    searchByCondition();
                    break;
                case 8:
                    handleProductInbox();
                    break;
                case 9:
                    subscribeToAType();
                    break;
            }
        } while (input != 10);
        userInterface.printMessage("Okay, bye!");
        logoutUser();
    }


    private void subscribeToAType() {
        ArrayList<String> type = TypeOfProduct.getAllTypesWithId();
        int input = userInterface.showAllTypeOfProducts(type);
        requestHandler.sendTypeOfSubToServer(input, user.getUserName());
        userInterface.printMessage("Successfully added");
    }


    private void logoutUser() {
        //requestHandler.saveLastLogIn(user.getUserName()); bortkommenterad f??r att spara notiser om man ej kollar inbox men loggar ut
        this.user = null;
        this.cart = null;
        this.productInbox = null;
        userInterface.showMainMenu();
        //System.exit(1);
    }


    private ArrayList<Product> getAllProducts(){
        ResponseMessage response = requestHandler.getAllProducts();
        if (response.getProducts().size() == 0) userInterface.printMessage("No result");
        else {
           return response.getProducts();
        }
        return null;
    }

    private void printAllProducts() {
        ResponseMessage response = requestHandler.getAllProducts();
        if (response.getProducts().size() == 0) userInterface.printMessage("No result");
        else {
            userInterface.showResult("YOUR RESULT", response.getProducts());
        }
    }


    private void createProductToSell() {
        String productName = userInterface.chooseProductName();
        double price = userInterface.getProductPrice();
        Condition condition = userInterface.getCondition();
        TypeOfProduct typeOfProduct = userInterface.getProductType();
        String colour = userInterface.getColor();
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
        if (cart.size() == 0) userInterface.printMessage("Your cart is empty at the moment");
        else {
            userInterface.showResult("------------YOUR CART------------", cart.getAllProductsInCart());
            boolean checkoutCart = userInterface.getBoolean("Do you want to checkout your cart? (true/false)");

            if(checkoutCart){
                ResponseMessage response = requestHandler.createOrdersFromCart(cart.getAllProductsInCart(), user);
                if(response.getSuccess()){
                    userInterface.printMessage("Your order has been submitted");
                }else{
                    userInterface.printMessage("Something went wrong");
                }
                cart.resetCart();
            }
        }
    }

    private void viewProducts() {
        ArrayList<Product> products = getAllProducts();
        userInterface.showResult("------------ALL PRODUCTS------------", products);
        if (products != null) {
            askUserToAddProductToCart(products);
        }
        else {
            userInterface.printMessage("There are no products.");
        }
    }

    private void askUserToAddProductToCart(ArrayList<?> products){
        Product newProduct = null;
        boolean wantToAddProduct = userInterface.getBoolean("Would you like to add product to your cart? (true/false)");
        if(wantToAddProduct){
            do {
                newProduct = (Product) userInterface.letUserChooseFromList(products);
            } while (newProduct == null);
            cart.addToCart(newProduct);
        }

    }

    private void searchByCondition() {
        Condition condition = userInterface.getCondition();
        ResponseMessage response = requestHandler.getSearchByCondition(condition);
        if (response.getProducts().size() == 0) userInterface.printMessage("No result");
        else {
            userInterface.showResult("------------YOUR RESULTS------------", response.getProducts());
            askUserToAddProductToCart(response.getProducts());
        }
    }


    private void searchByPriceRange() {
        double[] range = userInterface.getPriceRange();
        ResponseMessage response = requestHandler.getSearchByPriceResponse(range);
        if (response.getProducts().size() == 0) userInterface.printMessage("No result");
        else {
            userInterface.showResult("------------YOUR RESULTS------------", response.getProducts());
            askUserToAddProductToCart(response.getProducts());
        }
    }

    private void searchByProductType() {
        ResponseMessage response = requestHandler.getSearchByProductResponse(userInterface.getProductType());
        if (response.getProducts().size() == 0) userInterface.printMessage("No result");
        else {
            userInterface.showResult("------------YOUR RESULTS------------", response.getProducts());
            askUserToAddProductToCart(response.getProducts());
        }
    }


    private int getProductInboxSize(){
        int totalNotificSize = 0;
        ResponseMessage amountOfProducts = requestHandler.getAllProductsToConfirm(this.user);
        ResponseMessage newSubs = requestHandler.checkIfNewProductSub(this.user);
        if(newSubs.getSuccess()){
            totalNotificSize = 1;
        }
        totalNotificSize += amountOfProducts.getProducts().size();
        return totalNotificSize;
    }

    private void handleProductInbox() {
        ResponseMessage responseProductToConfirm = requestHandler.getAllProductsToConfirm(this.user);
        ResponseMessage newSubNotis = requestHandler.checkIfNewProductSub(this.user);
        if(!newSubNotis.getSuccess() && responseProductToConfirm.getProducts().size() == 0){
            userInterface.printMessage("You have no new messages");
        }
        if(newSubNotis.getSuccess()){
            userInterface.printMessage("You have new products to see that you are subscribing for! \n"
            + "----> Please go to the main menu to see them!");
        }

        if(responseProductToConfirm.getProducts().size() > 0){
            productInbox.update(responseProductToConfirm.getProducts());
            userInterface.printMessage("\nYou have products to sell!");
            Product product = (Product) userInterface.letUserChooseFromList(productInbox.getProductsToConfirm());
            Boolean acceptOrDecline = userInterface.getBoolean("Accept or decline? (True/False)");
            ResponseMessage responseMessage = requestHandler.confirmProduct(product, acceptOrDecline);
            if(responseMessage.getSuccess()){
                System.out.println("You succeeded with your task.");
            }
            productInbox.resetProductInbox();
        }
        requestHandler.saveLastLogIn(user.getUserName());
        user.setLastLogIn(String.valueOf(Timestamp.valueOf(LocalDateTime.now())));

    }
}
