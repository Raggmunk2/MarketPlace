package client.controller;

import shared.*;

import java.util.ArrayList;

public class RequestHandler {

    private client.controller.ServerConnection serverConnection;

    public RequestHandler(String host, int port){
        this.serverConnection = new client.controller.ServerConnection(host, port);
    }

    protected ResponseMessage getLoginResponse(String username, String password) {
        RequestMessage request = new RequestMessage(TypeOfMessage.LOGIN, username, password);
        return serverConnection.sendRequest(request);
    }

    protected ResponseMessage getRegisterResponse(User user) {
        RequestMessage request = new RequestMessage(TypeOfMessage.REGISTER, user);
        return serverConnection.sendRequest(request);
    }

    public ResponseMessage getSearchByProductResponse(TypeOfProduct typeOfProduct) {
        RequestMessage request = new RequestMessage(TypeOfMessage.SEARCH_BY_TYPE, typeOfProduct);
        return serverConnection.sendRequest(request);
    }

    public ResponseMessage getSearchByPriceResponse(double[] priceRange) {
        RequestMessage request = new RequestMessage(TypeOfMessage.SEARCH_BY_PRICE, priceRange);
        return serverConnection.sendRequest(request);
    }

    public ResponseMessage getSearchByCondition(Condition condition) {
        RequestMessage request = new RequestMessage(TypeOfMessage.SEARCH_BY_CONDITION, condition);
        return serverConnection.sendRequest(request);
    }

    public ResponseMessage createOrdersFromCart(ArrayList<Product> productsInCart, User user) {
        RequestMessage requestMessage = new RequestMessage(TypeOfMessage.CREATE_ORDER, productsInCart, user);
        return serverConnection.sendRequest(requestMessage);
    }

    public ResponseMessage createProductToSell(Product product) {
        RequestMessage requestMessage = new RequestMessage(TypeOfMessage.CREATE_PRODUCT_FOR_SELLING, product);
        return serverConnection.sendRequest(requestMessage);
    }


    public ResponseMessage getAllProducts() {
        RequestMessage requestMessage = new RequestMessage(TypeOfMessage.PRODUCTS);
        ResponseMessage responseMessage = serverConnection.sendRequest(requestMessage);
        return responseMessage;
    }

    public ResponseMessage getAllProductsToConfirm(User user) {
        RequestMessage requestMessage = new RequestMessage(TypeOfMessage.PRODUCTS_TO_CONFIRM, user);
        return serverConnection.sendRequest(requestMessage);
    }

    public ResponseMessage confirmProduct(Product product, Boolean acceptOrDecline) {
       RequestMessage requestMessage = new RequestMessage(TypeOfMessage.CONFIRM_PRODUCT, product, acceptOrDecline);
        return serverConnection.sendRequest(requestMessage);
    }

    public ResponseMessage sendTypeOfSubToServer(int input, String userName) {
        RequestMessage requestMessage = new RequestMessage(TypeOfMessage.SUBSCRIBE_TO_TYPE, input, userName);
        return serverConnection.sendRequest(requestMessage);
    }

    public ResponseMessage checkIfNewProductSub(User user) {
        RequestMessage requestMessage = new RequestMessage(TypeOfMessage.NOTIFICATION, user);
        ResponseMessage responseMessage = serverConnection.sendRequest(requestMessage);
        return serverConnection.sendRequest(requestMessage);
    }

    public ResponseMessage getWhenLoggedIn(String username){
        RequestMessage requestMessage = new RequestMessage(TypeOfMessage.GET_WHEN_LOGGED_IN, username);
        return serverConnection.sendRequest(requestMessage);
    }
    public ResponseMessage saveLastLogIn(String userName) {
        RequestMessage requestMessage = new RequestMessage(TypeOfMessage.SAVE_LAST_LOG_IN,userName);
        return serverConnection.sendRequest(requestMessage);
    }
}
