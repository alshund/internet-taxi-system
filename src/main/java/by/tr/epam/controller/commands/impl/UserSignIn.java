package by.tr.epam.controller.commands.impl;

import by.tr.epam.controller.commands.Command;
import by.tr.epam.domain.AuthenticationData;
import by.tr.epam.domain.TagAttributes;
import by.tr.epam.service.AuthorizationService;
import by.tr.epam.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSignIn implements Command {
    private AuthorizationService authorizationService;

    public UserSignIn() {

        ServiceFactory instance = ServiceFactory.getInstance();
        authorizationService = instance.getAuthorizationService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        AuthenticationData authenticationData = formAuthenticationData(request);
        String role = authorizationService.signIn(authenticationData);
        if (role != null) {

        } else {

        }
    }

    private AuthenticationData formAuthenticationData(HttpServletRequest request) {

        return new AuthenticationData(request.getParameter(TagAttributes.email.name()),
                                      request.getParameter(TagAttributes.password.name()));
    }

    private void successfulSignIn( ) {

    }

    private void failedSignIn() {

    }
}