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
    private RiderService riderService;

    public RiderSignUp() {

        ServiceFactory instance = ServiceFactory.getInstance();
        riderService = instance.getRiderService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Rider rider = createRider(request);
        riderService.signUp(rider);
    }

    private Rider createRider(HttpServletRequest request) {

        return new Rider(request.getParameter(TagAttributes.firstName.name()),
                         request.getParameter(TagAttributes.lastName.name()),
                         request.getParameter(TagAttributes.phoneNumber.name()),
                         request.getParameter(TagAttributes.email.name()),
                         request.getParameter(TagAttributes.password.name()));
    }
}
