package by.tr.epam.controller.commands.impl;

import by.tr.epam.controller.commands.Command;
import by.tr.epam.domain.SignInApplication;
import by.tr.epam.domain.TagAttributes;
import by.tr.epam.service.RiderService;
import by.tr.epam.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RiderSignIn implements Command {
    private RiderService service;

    public RiderSignIn() {

        ServiceFactory instance = ServiceFactory.getInstance();
        service = instance.getRiderService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        SignInApplication application = createApplication(request);
        if (service.signIn(application)) {

        } else {

        }
    }

    private SignInApplication createApplication(HttpServletRequest request) {

        return new SignInApplication(request.getParameter(TagAttributes.email.name()),
                                     request.getParameter(TagAttributes.password.name()));
    }
}
