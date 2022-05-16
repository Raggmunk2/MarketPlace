package client.view;

import shared.*;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);

    /*-------------------------When not logged in------------------------*/
    public int showMainMenu() {
        System.out.println("*-------------------- Welcome to FLEAS Market Place --------------------*");
        System.out.println("1: Log In.");
        System.out.println("2: Register");
        System.out.println("3: Exit.");
        System.out.println("*------------------- Please insert choose a number -------------------*");
        return scanner.nextInt();
    }

    // ------------------------------- User login -------------------------------

    public String getUsername() {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine() + scanner.nextLine();
        return username;
    }

    public String getPassword() {
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        return password;
    }

    // ------------------------------- Register  -------------------------------
    public String getFirstName() {
        System.out.println("First name:");
        String newUserFirstname = scanner.nextLine() + scanner.nextLine();
        return newUserFirstname;
    }

    public String getLastName() {
        System.out.println("Last name:");
        String newUserLastName = scanner.nextLine();
        return newUserLastName;
    }

    public java.sql.Date getDateOfBirth() {
        java.sql.Date dateOfBirth = null;
        try {
            System.out.println("Date of birth (yyyy-mm-dd):");
            String newUserdateOfBirth = scanner.nextLine();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(newUserdateOfBirth); // convert from string to date
            dateOfBirth = new java.sql.Date(date.getTime()); // convert javaDate to sqlDate
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateOfBirth;
    }

    public String getEmail() {
        System.out.println("Email:");
        String newUserEmail = scanner.nextLine();
        return newUserEmail;
    }

    /*-------------------------When logged in------------------------*/

    public int showLoggedInMenu(int productInbox) {
        System.out.println("*-------------------- Welcome what do you want to do now? --------------------*");
        System.out.println("1: Add product to cart");           //TODO detta blir view all products
        System.out.println("2: View Cart and checkout");
        System.out.println("3: Order history.");                //Finns i SOA
        System.out.println("4: Create a new order.");           //TODO ska tas bort(kommer hända genom kundkorgen)
        System.out.println("5: Sell a product.");
        System.out.println("6: See all products.");
        System.out.println("7: Search for products by type.");
        System.out.println("8: Search for products by price");
        System.out.println("9: Search products by condition");
        System.out.println("10: Product inbox: " + productInbox + " new messages.");                        //Inte implementerat
        System.out.println("11: Subscribe to a type");
        System.out.println("12: Log out.");
        System.out.println("*------------------------------ Please insert choose a number -----------------------------*");
        return scanner.nextInt();
    }

    public String chooseProductName() {
        System.out.println("*------------ Sell a product ------------*");
        System.out.println("Please write the name or title of the product");
        scanner.nextLine();
        String name = scanner.nextLine();
        return name;
    }

    public int chooseYearOfMaking(){
        System.out.println("*------------ Year Of Making ------------*");
        boolean fail;
        int price = 0;
        do{
            System.out.println("Please write the year of making of the product in this format: YYYY");
            String priceString = scanner.nextLine();
            try{
                price = Integer.parseInt(priceString);
                fail = false;
            }catch (Exception e){
                fail = true;
            }
        }while (fail);

        return price;
    }

    public double getProductPrice(){
        System.out.println("*------------ Price ------------*");
        boolean fail;
        double price = 0;
        do{
            System.out.println("Please write the price of the product int this format: 00.00");
            String priceString = scanner.nextLine();
            try{
                price = Double.parseDouble(priceString);
                fail = false;
            }catch (Exception e){
                fail = true;
            }
        }while (fail);

        return price;
    }


    public TypeOfProduct getProductType() {
        System.out.println("*------------ Types ------------*");
        System.out.println("Please write the number of the type you would like.");
        for (String type : TypeOfProduct.getAllTypesWithId()) {
            System.out.println(type);
        }

        return EnumHandler.getType(scanner.nextInt());
    }

    public double[] getPriceRange() {
        double[] range = new double[2];
        System.out.println("Type min prize");
        range[0] = scanner.nextInt();
        System.out.println("Type max prize");
        range[1] = scanner.nextInt();
        return range;
    }

    public String getColor(){
        System.out.println("*------------ Colour ------------*");
        System.out.println("Please write the colour you want.");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public Condition getCondition() {
        System.out.println("*------------ Condition ------------*");
        System.out.println("Please write the number of the condition you would like.");
        for (String condition : Condition.getAllConditionsWithId()) {
            System.out.println(condition);
        }

        return EnumHandler.getCondition(scanner.nextInt());

    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void showResult(String text, ArrayList<?> list) {
        System.out.println(text);
        for (Object p : list) {
            System.out.println(p);
        }
    }

    public Object letUserChooseFromList(ArrayList<?> list) {
        System.out.println("-------- TYPE ID TO CHOOSE ITEM--------------");
        for (Object object : list) {
            System.out.println(object.toString());
        }
        int selectedId = scanner.nextInt();
        int itemId = 0;
        for (Object object : list) {
            if (object instanceof Product) {
                itemId = ((Product) object).getId();
            } else if (object instanceof Order) {
                itemId = ((Order) object).getOrderId();
            }
            if (selectedId == itemId) {
                return object;
            }
        }
        System.out.println("Invalid product id");
        return null;
    }

    public boolean getBoolean(String text){
        System.out.println(text);
        return scanner.nextBoolean();
    }

    public int showAllTypeOfProducts(ArrayList<String> type){
        for (String s: type
             ) {
            System.out.println(s);
        }

        System.out.println("Please write a number that you want to subscribe to: ");
        int selectType = scanner.nextInt();
        return selectType;

    }
}
