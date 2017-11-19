package by.tr.epam.controller.commands.impl;

import by.tr.epam.controller.commands.Command;
import by.tr.epam.domain.AuthenticationData;
import by.tr.epam.domain.TagAttributes;
import by.tr.epam.service.RegistrationService;
import by.tr.epam.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserSignUp implements Command {
    private RegistrationService registrationService;

    public UserSignUp() {

        ServiceFactory instance = ServiceFactory.getInstance();
        registrationService = instance.getRegistrationService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        AuthenticationData authenticationData = formAuthenticationData(request);
        String id = registrationService.signUp(authenticationData);
        if (id != null) {
            successfulSignUp(id, request, response);
        } else {
            failedSignUp(request, response);
        }
    }

    private AuthenticationData formAuthenticationData(HttpServletRequest request) {

        return new AuthenticationData(request.getParameter(TagAttributes.email.name()),
                                      request.getParameter(TagAttributes.password.name()),
                                      request.getParameter(TagAttributes.role.name()));
    }

    private void successfulSignUp(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        response.sendRedirect( "driver");
    }

    private void failedSignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect("/RiderSignIn");
    }
}
