package shared;

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
        ArrayList<String> typeList = new ArrayList<>();
        int id = 1;
        for (TypeOfProduct type:TypeOfProduct.values()) {
            if(!type.equals(typeList)){
                typeList.add(id+". "+type.type);
                id++;
            }

        }
        return typeList;
    }
}
