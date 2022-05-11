package client.model;

import shared.Order;
import shared.Product;

import java.util.ArrayList;

/**
 * @Author: Linn Borgström
 * Class that holds the messages for notification
 */
public class Inbox {
    private ArrayList<Product> productsToConfirm = new ArrayList<>();
    private ArrayList<Order> acceptedOrders = new ArrayList<Order>();

    public void addToInboxProductsToConfirm(ArrayList<Product> products) {
        for (Product p:products ) {
            productsToConfirm.add(p);
        }
    }

    public boolean removeProductToConfirm(Product product){
        boolean success = false;
        for (Product p: productsToConfirm) {
            if(product.getId() == p.getId()){
                productsToConfirm.remove(p);
                success = true;
            }
        }
        return success;
    }
}
