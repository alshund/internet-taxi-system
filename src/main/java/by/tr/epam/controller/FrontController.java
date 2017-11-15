package by.tr.epam.controller;

import by.tr.epam.controller.commands.Command;
import by.tr.epam.controller.commands.CommandDirector;
import by.tr.epam.domain.TagAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private CommandDirector commandDirector = new CommandDirector();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getParameter(TagAttributes.command.name());
        Command command = commandDirector.getCommand(commandName);
        command.execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
