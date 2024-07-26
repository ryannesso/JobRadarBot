package com.github.ryannesso.jrtb.command;

import com.github.ryannesso.jrtb.service.SendMessageService;
import static com.github.ryannesso.jrtb.command.CommandName.*;

import java.util.HashMap;


public class CommandContainer {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final Command UnknownCommand;
    public CommandContainer(SendMessageService sendMessageService) {
        commands.put(START.getCommandName(), new StartCommand(sendMessageService));
        commands.put(STOP.getCommandName(), new StopCommand(sendMessageService));
        commands.put(HELP.getCommandName(), new HelpCommand(sendMessageService));
        commands.put(NO.getCommandName(), new NoCommand(sendMessageService));

        UnknownCommand = new UnknownCommand(sendMessageService);
    }
    public Command retriveCommand(String commandName) {
        // Получение команды или возвращение unknownCommand, если команда не найдена
        return commands.getOrDefault(commandName, UnknownCommand);
    }
}
