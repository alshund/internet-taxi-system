package by.tr.epam.controller.commands;

import by.tr.epam.controller.commands.impl.UserSignIn;
import by.tr.epam.domain.TagAttributes;
import by.tr.epam.controller.commands.impl.DriverSignUp;
import by.tr.epam.controller.commands.impl.UserSignUp;

import java.util.Map;
import java.util.HashMap;

public class CommandDirector {
    private Map<String, Command> commandMap = new HashMap<>();

    public CommandDirector() {

        commandMap.put(TagAttributes.userSignIn.name(), new UserSignIn());
        commandMap.put(TagAttributes.userSignUp.name(), new UserSignUp());
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }
}
