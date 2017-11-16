package by.tr.epam.controller.commands.impl;

import by.tr.epam.domain.TagAttributes;
import by.tr.epam.controller.commands.Command;
import by.tr.epam.domain.Rider;
import by.tr.epam.service.RiderService;
import by.tr.epam.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RiderSignUp implements Command {
    private RiderService service;

    public RiderSignUp() {

        ServiceFactory instance = ServiceFactory.getInstance();
        service = instance.getRiderService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Rider rider = createRider(request);
        if (service.signUp(rider)) {
            successfulSignUp(response);
        } else {
            failedSignUp(response);
        }
    }

    private Rider createRider(HttpServletRequest request) {

        return new Rider(request.getParameter(TagAttributes.firstName.name()),
                         request.getParameter(TagAttributes.lastName.name()),
                         request.getParameter(TagAttributes.phoneNumber.name()),
                         request.getParameter(TagAttributes.email.name()),
                         request.getParameter(TagAttributes.password.name()));
    }

    private void successfulSignUp(HttpServletResponse response) throws IOException {

        response.sendRedirect( "/success");
    }

    private void failedSignUp(HttpServletResponse response) throws IOException {

        response.sendRedirect("/RiderSignIn");
    }
}
