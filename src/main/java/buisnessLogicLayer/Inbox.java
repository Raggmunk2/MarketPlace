package buisnessLogicLayer;
import shared.Product;
import shared.TypeOfProduct;

import java.util.ArrayList;

/**
 * @Author: Linn Borgström
 * Class that holds the messages for notification
 */
public class Inbox {
    private ArrayList<Product> productToConfirm = new ArrayList<>();
    private ArrayList<TypeOfProduct> availableTypes = new ArrayList<>();

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
    public void addMessageToCheckTypes(TypeOfProduct typeOfProduct){
        availableTypes.add(typeOfProduct);
    }
    public boolean deleteMessage(){
        availableTypes.clear();
        if(availableTypes == null){
            return true;
        } else{
            return false;
        }

    }
}
