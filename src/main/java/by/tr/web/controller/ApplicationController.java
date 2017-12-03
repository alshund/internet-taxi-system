package by.tr.web.controller;

import by.tr.web.domain.DriverApplication;
import by.tr.web.domain.TagAttributes;
import by.tr.web.service.IdentificationService;
import by.tr.web.service.ServiceFactory;
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

public class ApplicationController extends HttpServlet {
    private DiskFileItemFactory diskFileItemFactory;
    private final int THRESHOLD_SIZE = 1024;
    private final String TEMP_DIR_PATH = "javax.servlet.context.tempdir";

    private IdentificationService identificationService;

    public ApplicationController() {

        ServiceFactory instance = ServiceFactory.getInstance();
        identificationService = instance.getIdentificationService();
    }

    @Override
    public void init() throws ServletException {

        diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(THRESHOLD_SIZE);
        File tempDir = (File)getServletContext().getAttribute(TEMP_DIR_PATH);
        diskFileItemFactory.setRepository(tempDir);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            List requestItems = fileUpload.parseRequest(req);
            Iterator itemIterator = requestItems.iterator();
            DriverApplication driverApplication = formDriverApplication(itemIterator);
            identificationService.signUp(driverApplication);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    private DriverApplication formDriverApplication(Iterator itemIterator) throws IOException {

        DriverApplication driverApplication = new DriverApplication();
        while(itemIterator.hasNext()) {
            FileItem item = (FileItem) itemIterator.next();
            TagAttributes fieldName = TagAttributes.valueOf(item.getFieldName().toUpperCase());
            setNeededParameter(driverApplication, item, fieldName);
        }
        return driverApplication;
    }

    private void setNeededParameter(DriverApplication driverApplication, FileItem item, TagAttributes fieldName) throws IOException {

        switch (fieldName) {
            case FIRST_NAME:
                driverApplication.setFirstName(item.getString());
                break;
            case LAST_NAME:
                driverApplication.setLastName(item.getString());
                break;
            case PATRONYMIC:
                driverApplication.setPatronymic(item.getString());
                break;
            case PHONE_NUMBER:
                driverApplication.setPhoneNumber(item.getString());
                break;
            case EMAIL:
                driverApplication.setEmail(item.getString());
                break;
            case DRIVER_LICENSE:
                driverApplication.setDriverLicense(item.getInputStream());
                break;
            case PASSPORT:
                driverApplication.setPassport(item.getInputStream());
                break;
        }
    }
}
