package by.tr.epam.controller.commands;

import by.tr.epam.controller.TagAttributes;
import by.tr.epam.controller.commands.impl.GoToAuthorization;
import by.tr.epam.controller.commands.impl.GoToRegistration;

import java.util.Map;
import java.util.HashMap;

public class CommandDirector {
    private Map<String, Command> commandMap = new HashMap<>();

    public CommandDirector() {
        commandMap.put(TagAttributes.goToRegistrationPage.name(), new GoToRegistration());
        commandMap.put(TagAttributes.goToAuthorizationPage.name(), new GoToAuthorization());
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }
}
