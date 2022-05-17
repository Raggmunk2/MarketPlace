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


    public static int getTypeAsInt(TypeOfProduct input) {
        int typeOfProduct;
        switch (input){
            case Hobby:
                typeOfProduct = 1;
                break;
            case Furniture :
                typeOfProduct = 2;
                break;
            case Computers :
                typeOfProduct = 3;
                break;
            case MobilePhones :
                typeOfProduct = 4;
                break;
            case EntertainmentEquipment :
                typeOfProduct = 5;
                break;
            case Cameras :
                typeOfProduct = 6;
                break;
            case HouseholdMachines :
                typeOfProduct = 7;
                break;
            case Clothing :
                typeOfProduct = 8;
                break;
            case SportsEquipment :
                typeOfProduct = 9;
                break;
            case KitchenUtensils :
                typeOfProduct = 10;
                break;
            case Jewelry :
                typeOfProduct = 11;
                break;
            case PerfumesAndCosmetics :
                typeOfProduct = 12;
                break;
            case Vehicle :
                typeOfProduct = 13;
                break;
            case Plants :
                typeOfProduct = 14;
                break;
            case Shoes :
                typeOfProduct = 15;
                break;
            case Tools :
                typeOfProduct = 16;
                break;
            case Other :
                typeOfProduct = 17;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + input);
        }
        return typeOfProduct;
    }
}
