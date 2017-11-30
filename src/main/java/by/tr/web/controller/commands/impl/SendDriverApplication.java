package by.tr.web.controller.commands.impl;

import by.tr.web.controller.commands.Command;
import by.tr.web.domain.TagAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SendDriverApplication implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Part part = request.getPart("file");
        System.out.println(part.getSubmittedFileName());
    }
}
