package com.github.ryannesso.jrtb.command;

import com.github.ryannesso.jrtb.service.SendMessageService;
import com.github.ryannesso.jrtb.service.TelegramUserService;
import static com.github.ryannesso.jrtb.command.CommandName.*;

import java.util.HashMap;

public class CommandContainer {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final Command unknownCommand;

    public CommandContainer(SendMessageService sendMessageService, TelegramUserService telegramUserService) {
        commands.put(START.getCommandName(), new StartCommand(sendMessageService, telegramUserService));
        commands.put(STOP.getCommandName(), new StopCommand(sendMessageService, telegramUserService));
        commands.put(HELP.getCommandName(), new HelpCommand(sendMessageService));
        commands.put(NO.getCommandName(), new NoCommand(sendMessageService));
        commands.put(STAT.getCommandName(), new StatCommand(sendMessageService, telegramUserService));

        unknownCommand = new UnknownCommand(sendMessageService);
    }

    public Command retrieveCommand(String commandName) {
        // Получение команды или возвращение unknownCommand, если команда не найдена
        return commands.getOrDefault(commandName, unknownCommand);
    }
}
