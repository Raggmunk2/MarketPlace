package client.view;

import buisnessLogicLayer.EnumHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);

    public int showMainMenu() {
        System.out.println("*-------------------- Welcome to FLEAS Market Place --------------------*");
        System.out.println("1: Log In.");
        System.out.println("2: Create an account.");
        System.out.println("3: Exit.");
        System.out.println("*------------------- Please insert choose a number -------------------*");
        return scanner.nextInt();
    }

    // ------------------------------- User login -------------------------------

    public String getUsername(){
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        scanner.nextLine();
        return username;

    }
    public String getPassword(){
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        return password;
    }
    public String getFirstName(){
        System.out.println("First name:");
        String newUserFirstname = scanner.nextLine();
        return newUserFirstname;
    }
    public String getLastName(){
        System.out.println("Last name:");
        String newUserLastName = scanner.nextLine();
        return newUserLastName;
    }
    public java.sql.Date getDateOfBirth() throws ParseException {
        System.out.println("Date of birth (yyyy-mm-dd):");
        String newUserdateOfBirth = scanner.nextLine();

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(newUserdateOfBirth); // convert from string to date
        java.sql.Date dob = new java.sql.Date(date.getTime()); // convert javaDate to sqlDate
        return dob;
    }
    public String getEmail(){
        System.out.println("Email:");
        String newUserEmail = scanner.nextLine();
        return newUserEmail;
    }

    // ------------------------------- Create new account  -------------------------------
   /* public void showCreateAccountMenu() throws ParseException {

        System.out.println("*--------------------- CREATE AN ACCOUNT --------------------*");

        System.out.println("First name:");
        String newUserFirstname = scanner.nextLine();
        System.out.println("Last name:");
        String newUserLastName = scanner.nextLine();
        System.out.println("Date of birth (yyyy-mm-dd):");
        String newUserdateOfBirth = scanner.nextLine();
        System.out.println("Email:");
        String newUserEmail = scanner.nextLine();
        System.out.println("Username:");
        String newUserName = scanner.nextLine();
        System.out.println("Password:");
        String newUserPassword = scanner.nextLine();

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(newUserdateOfBirth); // convert from string to date
        java.sql.Date dob = new java.sql.Date(date.getTime()); // convert javaDate to sqlDate

        //accessControl.addUserAccountToDatabase(newUserFirstname, newUserLastName, dob, newUserEmail, newUserName, newUserPassword);
        //TODO: show menu
    }*/

    /*-------------------------When logged in------------------------*/
    public void showLoggedInMenu(){
        int input;
        System.out.println("*-------------------- Welcome what do you want to do now? --------------------*");
        System.out.println("1: Order history.");
        System.out.println("2: Add order.");
        System.out.println("3: Sell a product.");
        System.out.println("4: See all products.");
        System.out.println("5: See products by type.");
        System.out.println("6: See products by price");
        System.out.println("7: See products by condition");
        System.out.println("8: See your inbox");
        System.out.println("9: Log out.");
        System.out.println("*------------------------------ Please insert choose a number -----------------------------*");
        input = scanner.nextInt();

        switch (input) {
            case 1: //see order history
                //accessControl.getOrderHistory();
                showLoggedInMenu();
                break;

            case 2: //submit order from cart or accept an order as a seller
                //lägg till order
                System.out.println("This is not available for now");
                break;
            case 3:
                //sell product
                showSellProductMenu();
                System.out.println("This is not available for now");
                break;
            case 4:
                //See product
                System.out.println("This is not available for now");
                break;
            case 5: //search for a product by type
                int typeId = showSearchByTypeMenu();
                //accessControl.searchProductByType(typeId); //vet ej om den ska vara genom accessControll eller productRepo
                break;
            case 6: //search for a product by price
                System.out.println("What is the minimum price? Please write in the format e.g., 10.00");
                int minPrice = scanner.nextInt();
                System.out.println("What is the miximum price? Please write in the format e.g., 10.00");
                int maxPrice = scanner.nextInt();
                //accessControl.searchProductByCondition(minPrice,maxPrice); // vet ej om det ska vara genom accessControll
                break;
            case 7: //search for product by condition
                int conditionId = showSearchByConditionMenu();
                //accessControl.searchProductByCondition(conditionId); //vet ej om den ska vara genom accessControll
                System.out.println("This is not available for now");
                break;
            case 8: //see your inbox
                System.out.println("This is not available for now");
                break;
            case 9: // Log out
                //TODO: Show menu
                break;
        }
    }

    private void showSellProductMenu() {
        System.out.println("*------------ Sell a product ------------*");
        System.out.println("Please write the name or title of the product");
        String productname = scanner.nextLine();
        System.out.println("Please write the price of the product int this format: 00.00");
        double price = scanner.nextFloat();
        System.out.println("Please write the number of the condition you like to mark your product by");
        EnumHandler.getAllConditions();
        int conditonId = scanner.nextInt();
        System.out.println("Please write the number of the type you like to mark your product by");
        EnumHandler.getAllTypes();
        int typeId = scanner.nextInt();
        System.out.println("Please write the year of making of the product in this format: YYYY ");
        int yearOfMaking = scanner.nextInt();
        System.out.println("Please write the colour of you product");
        String colour = scanner.nextLine();
        //accessControl.addProductToDB(productname,price,conditonId,typeId,yearOfMaking,colour);  //vet ej om den ska vara genom accessControll
    }

    private int showSearchByConditionMenu() {
        System.out.println("*------------ Condition ------------*");
        System.out.println("Please write the number of the condition you like to see");
        EnumHandler.getAllConditions();
        int input = scanner.nextInt();
        return input;
    }

    private int showSearchByTypeMenu() {
        System.out.println("*------------ Types ------------*");
        System.out.println("Please write the number of the type you like to see");
        EnumHandler.getAllTypes();
        int input = scanner.nextInt();
        return input;
    }
}
