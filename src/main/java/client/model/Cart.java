package client.model;

import shared.Product;

import java.util.ArrayList;

public class Cart {

    private ArrayList<Product> productList;

    public Cart(){
        productList = new ArrayList<>();
    }

    public void addToCart(Product product){
        productList.add(product);
    }

    public void resetCart(){
        productList.clear();
    }

    public ArrayList<Product> getAllProductsInCart() {
        return productList;
    }

    public int size() {
        return productList.size();
    }
}
