package buisnessLogicLayer;
import shared.TypeOfProduct;

import java.util.HashMap;
import java.util.Map;

/**
 * Ska f�rmodligen raderas, men sparar g�rna till senare
 */

public class FromEnumToNumb {

    private HashMap map;

    public void blablablabla (Enum ob){
        Enum anEnum = (Enum) new Object();

        HashMap<Enum, Integer> map = new HashMap<Enum, Integer>();

        for (Map.Entry<Enum, Integer> entry : map.entrySet()){
            entry.getKey();
            entry.getValue();
        }
    }

    //m�ste skicka in vilken enum ska kollas om en metod ska finnas f�r alla enums.
    public int fromEnumToInt(TypeOfProduct ty){
        TypeOfProduct[] arr = TypeOfProduct.values();
        int count = 0;
        for(TypeOfProduct t : arr){
            if(t == ty){
                return count;
            }
            count++;
        }
        return -1;
    }

    //bleh
    public int enumToInt(Enum ty){
        TypeOfProduct[] arr = TypeOfProduct.values();
        int count = 0;
        for(TypeOfProduct t : arr){
            if(t == ty){
                return count;
            }
            count++;
        }
        return -1;
    }

}
