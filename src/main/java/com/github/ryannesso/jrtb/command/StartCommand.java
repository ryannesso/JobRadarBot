package com.github.ryannesso.jrtb.command;

import com.github.ryannesso.jrtb.service.SendMessageService;
import com.github.ryannesso.jrtb.service.TelegramUserService;
import com.github.ryannesso.jrtb.repository.entity.TelegramUser;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {
    private final SendMessageService sendMessageService;
    private final TelegramUserService telegramUserService;

    public static final String START_MESSAGE = "Hello! I am the JobRadar Telegram Bot. I can help you find job vacancies that interest you :)";

    public StartCommand(SendMessageService sendMessageService, TelegramUserService telegramUserService) {
        this.sendMessageService = sendMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                });

        sendMessageService.sendMessage(chatId, START_MESSAGE);
    }
}