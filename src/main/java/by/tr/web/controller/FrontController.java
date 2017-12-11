package by.tr.web.controller;

import by.tr.web.controller.commands.Command;
import by.tr.web.controller.commands.CommandDirector;
import by.tr.web.domain.TagAttributes;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FrontController extends HttpServlet {
    private CommandDirector commandDirector = new CommandDirector();
    private Command command;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getParameter(TagAttributes.COMMAND.name().toLowerCase());
        command = commandDirector.getCommand(commandName);
        command.execute(req, resp);
    }

    @Override
    // и чем же у тебя метод doPost от doGet отличается
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getParameter(TagAttributes.COMMAND.name().toLowerCase());
        command = commandDirector.getCommand(commandName);
        command.execute(req, resp);
    }
}
