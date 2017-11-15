package by.tr.epam.controller.commands;

import by.tr.epam.domain.TagAttributes;
import by.tr.epam.controller.commands.impl.DriverSignIn;
import by.tr.epam.controller.commands.impl.DriverSignUp;
import by.tr.epam.controller.commands.impl.RiderSignIn;
import by.tr.epam.controller.commands.impl.RiderSignUp;

import java.util.Map;
import java.util.HashMap;

public class CommandDirector {
    private Map<String, Command> commandMap = new HashMap<>();

    public CommandDirector() {

        commandMap.put(TagAttributes.driverSignIn.name(), new DriverSignIn());
        commandMap.put(TagAttributes.driverSignUp.name(), new DriverSignUp());
        commandMap.put(TagAttributes.riderSignIn.name(), new RiderSignIn());
        commandMap.put(TagAttributes.riderSignUp.name(), new RiderSignUp());
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }
}
