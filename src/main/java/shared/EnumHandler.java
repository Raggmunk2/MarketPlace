package shared;

import shared.Condition;
import shared.TypeOfProduct;

import java.util.ArrayList;

public class EnumHandler {
/*
    /**
     * @Author : Linn Borgström
     * To get the correct condition
     * @param condition of the product
     * @return the condition in a enum
     */
    /*public static Condition getCondition(String condition) {
        Condition productCondition;
        switch (condition){
            case "New" :
                productCondition = Condition.New;
                break;
            case "Very Good" :
                productCondition = Condition.VeryGood;
                break;
            case "Not Working Properly" :
                productCondition = Condition.NotWorkingProperly;
                break;
            case "Good" :
                productCondition = Condition.Good;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + condition);
        }
        return productCondition;
    }*/

    public static Condition getCondition(int condition) {
        Condition productCondition;
        switch (condition){
            case 1 :
                productCondition = Condition.New;
                break;
            case 2 :
                productCondition = Condition.VeryGood;
                break;
            case 3 :
                productCondition = Condition.Good;
                break;
            case 4 :
                productCondition = Condition.NotWorkingProperly;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + condition);
        }
        return productCondition;
    }
/*
    /**
     * @Author: Linn Borgström
     * to get the correct type
     * @param type the type in a string
     * @return the type in an enum
     */
  /*  public static TypeOfProduct getType(String type) {
        TypeOfProduct typeOfProduct;
        switch (type){
            case "Hobby" :
                typeOfProduct = TypeOfProduct.Hobby;
                break;
            case "Furniture" :
                typeOfProduct = TypeOfProduct.Furniture;
                break;
            case "Cameras" :
                typeOfProduct = TypeOfProduct.Cameras;
                break;
            case "Clothing" :
                typeOfProduct = TypeOfProduct.Clothing;
                break;
            case "Computers" :
                typeOfProduct = TypeOfProduct.Computers;
                break;
            case "Entertainment equipment" :
                typeOfProduct = TypeOfProduct.EntertainmentEquipment;
                break;
            case "Household machines" :
                typeOfProduct = TypeOfProduct.HouseholdMachines;
                break;
            case "Jewelry" :
                typeOfProduct = TypeOfProduct.Jewelry;
                break;
            case "Mobile phones" :
                typeOfProduct = TypeOfProduct.MobilePhones;
                break;
            case "Perfumes and cosmetics" :
                typeOfProduct = TypeOfProduct.PerfumesAndCosmetics;
                break;
            case "Other" :
                typeOfProduct = TypeOfProduct.Other;
                break;
            case "Plants" :
                typeOfProduct = TypeOfProduct.Plants;
                break;
            case "Shoes" :
                typeOfProduct = TypeOfProduct.Shoes;
                break;
            case "Vehicle" :
                typeOfProduct = TypeOfProduct.Vehicle;
                break;
            case "Tools" :
                typeOfProduct = TypeOfProduct.Tools;
                break;
            case "Sports equipment" :
                typeOfProduct = TypeOfProduct.SportsEquipment;
                break;
            case "Kitchen utensils" :
                typeOfProduct = TypeOfProduct.KitchenUtensils;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return typeOfProduct;
    }*/

    public static TypeOfProduct getType(int input) {
        TypeOfProduct typeOfProduct;
        switch (input){
            case 1 :
                typeOfProduct = TypeOfProduct.Hobby;
                break;
            case 2 :
                typeOfProduct = TypeOfProduct.Furniture;
                break;
            case 3 :
                typeOfProduct = TypeOfProduct.Computers;
                break;
            case 4 :
                typeOfProduct = TypeOfProduct.MobilePhones;
                break;
            case 5 :
                typeOfProduct = TypeOfProduct.EntertainmentEquipment;
                break;
            case 6 :
                typeOfProduct = TypeOfProduct.Cameras;
                break;
            case 7 :
                typeOfProduct = TypeOfProduct.HouseholdMachines;
                break;
            case 8 :
                typeOfProduct = TypeOfProduct.Clothing;
                break;
            case 9 :
                typeOfProduct = TypeOfProduct.SportsEquipment;
                break;
            case 10 :
                typeOfProduct = TypeOfProduct.KitchenUtensils;
                break;
            case 11 :
                typeOfProduct = TypeOfProduct.Jewelry;
                break;
            case 12 :
                typeOfProduct = TypeOfProduct.PerfumesAndCosmetics;
                break;
            case 13 :
                typeOfProduct = TypeOfProduct.Vehicle;
                break;
            case 14 :
                typeOfProduct = TypeOfProduct.Plants;
                break;
            case 15 :
                typeOfProduct = TypeOfProduct.Shoes;
                break;
            case 16 :
                typeOfProduct = TypeOfProduct.Tools;
                break;
            case 17 :
                typeOfProduct = TypeOfProduct.Other;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + input);
        }
        return typeOfProduct;
    }


    /**
     * @Author Linn Borgström
     * @return an arraylist with the types and unique id
     */
    public static ArrayList getAllTypes() {
        return TypeOfProduct.getAllTypesWithId();

    }
    /**
     * @Author Linn Borgström
     * @return an arraylist with the condition and unique id
     */
    public static ArrayList getAllConditions(){
        return Condition.getAllConditionsWithId();
    }
}
