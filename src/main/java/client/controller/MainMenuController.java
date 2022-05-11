package client.controller;

import client.view.UserInterface;
import shared.ResponseMessage;
import shared.TypeOfMessage;
import shared.User;

import java.sql.Date;

public class MainMenuController {

    private UserInterface userInterface;
    private client.controller.RequestHandler requestHandler;
    private LoggedInController loggedInController;

    public MainMenuController() {
        this.userInterface = new UserInterface();
        this.requestHandler = new client.controller.RequestHandler("127.0.0.1", 6890);
        mainMenuHandler(userInterface.showMainMenu());
    }

    private void mainMenuHandler(int input) {
        switch (input) {
            case 1:
                User user = null;
                do {
                    user = loginUser();
                } while (user == null);
                new LoggedInController(user, this.userInterface, this.requestHandler);
                break;
            case 2:
                String firstName = userInterface.getFirstName();
                String lastName = userInterface.getLastName();
                Date DoB = userInterface.getDateOfBirth();
                String email = userInterface.getEmail();
                String newUsername = userInterface.getUsername();
                String newPassword = userInterface.getPassword();
                user = new User(newUsername, newPassword, firstName, lastName, DoB, email);
                ResponseMessage registerResponse = requestHandler.getRegisterResponse(user);
                Boolean success = registerResponse.getSuccess();
                if (success) {
                    System.out.println("Welcome " + user.getUserName() + " what would you like to do now?");
                    new LoggedInController(user, this.userInterface, this.requestHandler);
                } else {
                    System.out.println("Sorry we could not add the user, please try again.");
                    mainMenuHandler(userInterface.showMainMenu());
                }
            case 3:
                System.exit(0);
        }
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
}
