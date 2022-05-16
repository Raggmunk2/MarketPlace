package buisnessLogicLayer;
import shared.Product;

import java.util.ArrayList;

public class Inbox {

    private ArrayList<Product> productToConfirm = new ArrayList<>();

    public void addToInboxProductsToConfirm(ArrayList<Product> products) {
        for (Product p:products ) {
            productToConfirm.add(p);
        }
    }

    public boolean removeProductToConfirm(Product product){
        boolean success = false;
        for (Product p:productToConfirm) {
            if(product.getId() == p.getId()){
                productToConfirm.remove(p);
                success = true;
            }
        }
        return success;
    }

   /* public void addMessageToCheckTypes(TypeOfProduct typeOfProduct){
        availableTypes.add(typeOfProduct);
    }
    public boolean deleteMessage(){
        availableTypes.clear();
        if(availableTypes == null){
            return true;
        } else{
            return false;
        }

    }*/
}
