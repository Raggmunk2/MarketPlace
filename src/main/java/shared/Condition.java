package shared;

import java.util.ArrayList;

public enum Condition {
    New("New"),
    VeryGood("Very good"),
    Good("Good"),
    NotWorkingProperly("Not working properly");

    private String conditionName;

    Condition(String conditionName) {
        this.conditionName=conditionName;
    }

    @Override
    public String toString() {
        return conditionName;
    }


    public static ArrayList<String> getAllConditionsWithId(){
        ArrayList<String> allConditions = new ArrayList<>();
        int id = 1;
        for (Condition con:Condition.values()) {
            if(!con.equals(allConditions)){
                allConditions.add(id+". "+con.conditionName);
                id++;
            }

        }
        return allConditions;
    }


    public static int getConditionAsInt(Condition condition) {
        int productCondition;
        switch (condition){
            case New :
                productCondition = 1;
                break;
            case VeryGood :
                productCondition = 2;
                break;
            case Good :
                productCondition = 3;
                break;
            case NotWorkingProperly :
                productCondition = 4;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + condition);
        }
        return productCondition;
    }


}
