package client.model;

import shared.Product;

import java.util.ArrayList;

public class ProductInbox {

    private ArrayList<Product> productsToConfirm;

    public ProductInbox(){
        productsToConfirm = new ArrayList<>();
    }

    public void update(ArrayList<Product> products){
        for(Product p: products){
            productsToConfirm.add(p);
        }
    }

    public ArrayList<Product> getProductsToConfirm() {
        return productsToConfirm;
    }

    public void resetProductInbox() {
        productsToConfirm.clear();
    }

    public int size(){
        return productsToConfirm.size();
    }
}
