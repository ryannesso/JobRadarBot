package com.github.ryannesso.jrtb.command;

import com.github.ryannesso.jrtb.service.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {
    private final SendMessageService sendMessageService;

    public static final String START_MESSAGE = "I don't know that command, please use /help";

    public NoCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
