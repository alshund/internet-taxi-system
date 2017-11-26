package by.tr.web.controller.commands.impl;

import by.tr.web.controller.PagesBundleKeys;
import by.tr.web.controller.commands.Command;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.TagAttributes;
import by.tr.web.service.RegistrationService;
import by.tr.web.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

public class UserSignUp implements Command {
    private RegistrationService registrationService;

    public UserSignUp() {

        ServiceFactory instance = ServiceFactory.getInstance();
        registrationService = instance.getRegistrationService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ResourceBundle bundle = ResourceBundle.getBundle(PagesBundleKeys.BUNDLE_NAME);
        AuthenticationData authenticationData = formAuthenticationData(request);
        String role = registrationService.signUp(authenticationData);
        if (role != null) {
            successfulSignUp(role, request, response);
        } else {
            failedSignUp(bundle, response);
        }
    }

    private AuthenticationData formAuthenticationData(HttpServletRequest request) {

        return new AuthenticationData(request.getParameter(TagAttributes.email.name()),
                                      request.getParameter(TagAttributes.password.name()),
                                      request.getParameter(TagAttributes.role.name()));
    }

    private void successfulSignUp(String role, HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        response.sendRedirect( role);
    }

    private void failedSignUp(ResourceBundle bundle, HttpServletResponse response) throws IOException {

        System.out.println("FA");
        response.sendRedirect(bundle.getString(PagesBundleKeys.INDEX_PAGE));
    }
}
