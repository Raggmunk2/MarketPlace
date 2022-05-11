package client.controller;

import shared.User;
import shared.*;
import client.view.UserInterface;

import java.sql.Date;
import java.text.ParseException;

public class Controller {

    private UserInterface userInterface;
    private ServerConnection connection;
    private User user;

    public Controller() throws ParseException {
        connection = new ServerConnection("127.0.0.1", 6890);
        userInterface = new UserInterface();
        menuHandler(userInterface.showMainMenu());
    }

    private void menuHandler(int input ) throws ParseException {
        //TODO Make to switch
        if(input == 1){ //LOGGA IN
            String username = userInterface.getUsername();
            String password = userInterface.getPassword();
            RequestMessage logInRequest = new RequestMessage("Login", username, password);
            ReturnMessage response = connection.sendRequest(logInRequest);
            System.out.println(response);
            if(response != null){
                this.user = response.getUser();
                System.out.println(user.getUserName());
                userInterface.showLoggedInMenu();
            }else{
                userInterface.showMainMenu();
            }
        }else if(input == 2){ //SKAPA EN ANVÄNDARE
            String firstName = userInterface.getFirstName();
            String lastName = userInterface.getLastName();
            Date DoB = userInterface.getDateOfBirth();
            String email = userInterface.getEmail();
            String username = userInterface.getUsername();
            String password = userInterface.getPassword();

            RequestMessage createAccountReq = new RequestMessage("CreateAccount",firstName, lastName, DoB, email, username, password);
            ReturnMessage response = connection.sendRequest(createAccountReq);
            System.out.println(response);

        }else{
            System.exit(0);
        }
    }
}
