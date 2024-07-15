package com.github.ryannesso.jrtb.command;

import com.github.ryannesso.jrtb.service.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements Command {
    private final SendMessageService sendMessageService;

    public static final String START_MESSAGE = "command list: \n /start \n /stop";

    public HelpCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}