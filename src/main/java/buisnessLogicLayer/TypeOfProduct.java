package buisnessLogicLayer;

import java.util.ArrayList;

public enum TypeOfProduct {

    Hobby("Hobby"),
    Furniture("Furniture"),
    Computers("Computers"),
    MobilePhones("Mobile phones"),
    EntertainmentEquipment("Entertainment equipment"),
    Cameras("Cameras"),
    HouseholdMachines("Household machines"),
    Clothing("Clothing"),
    SportsEquipment("Sports equipment"),
    KitchenUtensils("Kitchen utensils"),
    Jewelry("Jewelry"),
    PerfumesAndCosmetics("Perfumes and cosmetics"),
    Vehicle("Vehicle"),
    Plants("Plants"),
    Shoes("Shoes"),
    Tools("Tools"),
    Other("Other");

    private String type;



    TypeOfProduct(String s) {
        this.type = s;
    }

    @Override
    public String toString() {
        String toString = type;
        return toString;
    }

    public static ArrayList<String> getAllTypesWithId(){
        ArrayList<String> allTypes = new ArrayList<>();
        int id = 1;
        for (TypeOfProduct top:TypeOfProduct.values()) {
            if(!top.equals(allTypes)){
                allTypes.add(id+". "+top.type + "\n");
                id++;
            }

        }
        return allTypes;
    }

    public static void main(String[] args) {
        //System.out.println(Arrays.stream(TypeOfProduct.values()).spliterator());

        System.out.println(TypeOfProduct.getAllTypesWithId());
    }
}
