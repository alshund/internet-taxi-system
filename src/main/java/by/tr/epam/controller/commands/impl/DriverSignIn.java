package by.tr.epam.controller.commands.impl;

import by.tr.epam.controller.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DriverSignIn implements Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/htmlPages/RiderSignIn.jsp");
        dispatcher.forward(request, response);
    }
}
