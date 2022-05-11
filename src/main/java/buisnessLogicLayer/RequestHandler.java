package buisnessLogicLayer;

import dataAccessLayer.AccessControlRepository;
import shared.*;

import java.util.Objects;

public class RequestHandler {

    public ReturnMessage handleRequest(RequestMessage request) {
        ReturnMessage response = null;
        if (request instanceof RequestMessage) {
            String typeOfMessage = ((RequestMessage) request).getTypeOfMessage();
            if (Objects.equals(typeOfMessage, "Login")) {
                AccessControlRepository accessControlRepository = new AccessControlRepository();
                User user = accessControlRepository.checkLogin(request.getUserName(), request.getPassword());
                System.out.println(user);
                System.out.println(user.getUserName());
                response = new ReturnMessage("LoginResponse", user);
            }else if(Objects.equals(typeOfMessage, "CreateAccount")){

            }
        }
        return response;
    }
}
