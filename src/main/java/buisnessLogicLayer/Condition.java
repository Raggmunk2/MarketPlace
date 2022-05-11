package buisnessLogicLayer;

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
                allConditions.add(id+". "+con.conditionName + "\n");
                id++;
            }

        }
        return allConditions;
    }


}
