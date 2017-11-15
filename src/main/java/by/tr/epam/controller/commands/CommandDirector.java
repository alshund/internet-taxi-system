package by.tr.epam.controller.commands;

import by.tr.epam.controller.TagAttributes;
import by.tr.epam.controller.commands.impl.RiderSignIn;
import by.tr.epam.controller.commands.impl.DriverSignIn;

import java.util.Map;
import java.util.HashMap;

public class CommandDirector {
    private Map<String, Command> commandMap = new HashMap<>();

    public CommandDirector() {
        commandMap.put(TagAttributes.driverSignIn.name(), new DriverSignIn());
        commandMap.put(TagAttributes.riderSignIn.name(), new RiderSignIn());
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }
}
