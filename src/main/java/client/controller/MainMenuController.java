package client.controller;

import client.view.UserInterface;
import shared.ResponseMessage;
import shared.TypeOfMessage;
import shared.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MainMenuController {

    private UserInterface userInterface;
    private RequestHandler requestHandler;

    public MainMenuController() {
        this.userInterface = new UserInterface();
        this.requestHandler = new client.controller.RequestHandler("127.0.0.1", 6890);
        mainMenuHandler();
    }

    private void mainMenuHandler() {
        int input;
        User user = null;
        do {
            input = userInterface.showMainMenu();
            switch (input) {
                case 1:
                    do {
                        user = loginUser();
                        //user.setLastLogIn(String.valueOf(Timestamp.valueOf(LocalDateTime.now())));
                    } while (user == null);
                    new LoggedInController(user, this.userInterface, this.requestHandler);
                    break;
                case 2:
                    user = register();
                    if (user != null) {
                        userInterface.printMessage("Welcome " + user.getUserName() + " what would you like to do now?\"");
                        new LoggedInController(user, this.userInterface, this.requestHandler);
                    }
            }
        }while (input != 3);
        System.exit(0);
    }

    private User loginUser() {
        String username = userInterface.getUsername();
        String password = userInterface.getPassword();
        ResponseMessage loginResponse = requestHandler.getLoginResponse(username, password);
        if (loginResponse != null) {
            if (loginResponse.getTypeOfMessage() == TypeOfMessage.ERROR) {
                userInterface.printMessage("Something went wrong. Try again.");
                return null;
            }
        }
        return loginResponse.getUser();
    }

    private User register() {
        String firstName = userInterface.getFirstName();
        String lastName = userInterface.getLastName();
        Date DoB = userInterface.getDateOfBirth();
        String email = userInterface.getEmail();
        String newUsername = userInterface.getUsername();
        String newPassword = userInterface.getPassword();
        User user = new User(newUsername, newPassword, firstName, lastName, DoB, email, String.valueOf(Timestamp.valueOf(LocalDateTime.now())));
        System.out.println("from register: " + Timestamp.valueOf(LocalDateTime.now()));
        ResponseMessage registerResponse = requestHandler.getRegisterResponse(user);
        Boolean success = registerResponse.getSuccess();
        if (!success) {
            userInterface.printMessage("Sorry we could not add the user, please try again.");
            return null;
        }
        return user;
    }
}
