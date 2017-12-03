package by.tr.web.controller.commands.impl;

import by.tr.web.controller.commands.Command;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.TagAttributes;
import by.tr.web.service.IdentificationService;
import by.tr.web.service.ServiceFactory;
import by.tr.web.service.exception.AuthenticationException;
import by.tr.web.service.exception.UserServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSignIn implements Command {
    private IdentificationService identificationService;

    public UserSignIn() {

        ServiceFactory instance = ServiceFactory.getInstance();
        identificationService = instance.getIdentificationService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            AuthenticationData authenticationData = formAuthenticationData(request);
            String role = identificationService.signIn(authenticationData);

        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (UserServiceException e) {
            e.printStackTrace();
        }
    }

    private AuthenticationData formAuthenticationData(HttpServletRequest request) {

        return new AuthenticationData(request.getParameter(TagAttributes.EMAIL.name().toLowerCase()),
                                      request.getParameter(TagAttributes.PASSWORD.name().toLowerCase()));
    }

    private void successfulSignIn( ) {

    }

    private void failedSignIn() {

    }
}
