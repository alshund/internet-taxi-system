package by.tr.web.controller.commands;

import by.tr.web.controller.commands.impl.UserSignIn;
import by.tr.web.domain.TagAttributes;
import by.tr.web.controller.commands.impl.UserSignUp;

import java.util.Map;
import java.util.HashMap;

public class CommandDirector {
    private Map<String, Command> commandMap = new HashMap<>();

    public CommandDirector() {

        commandMap.put(TagAttributes.USER_SIGN_IN.name().toLowerCase(), new UserSignIn());
        commandMap.put(TagAttributes.USER_SIGN_UP.name().toLowerCase(), new UserSignUp());
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }
}
