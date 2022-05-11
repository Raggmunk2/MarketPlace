package buisnessLogicLayer;

import java.util.ArrayList;

public class EnumHandler {
    /**
     * @Author : Linn Borgström
     * To get the correct dondition
     * @param condition of the product
     * @return the condition in a enum
     */
    public static Condition getCondition(String condition) {
        Condition productCondition;
        switch (condition){
            case "New" :
                productCondition = Condition.New;
                break;
            case "Very good" :
                productCondition = Condition.VeryGood;
                break;
            case "Not working properly" :
                productCondition = Condition.NotWorkingProperly;
                break;
            case "Good" :
                productCondition = Condition.Good;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + condition);
        }
        return productCondition;
    }

    /**
     * @Author: Linn Borgström
     * to get the correct type
     * @param type the type in a string
     * @return the type in an enum
     */
    public static buisnessLogicLayer.TypeOfProduct getType(String type) {
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
