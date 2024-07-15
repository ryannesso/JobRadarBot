package com.github.ryannesso.jrtb.bot;

import com.github.ryannesso.jrtb.command.HelpCommand;
import com.github.ryannesso.jrtb.command.NoCommand;
import com.github.ryannesso.jrtb.command.StartCommand;
import com.github.ryannesso.jrtb.command.StopCommand;
import com.github.ryannesso.jrtb.service.SendMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class JobRadarTelegramBot extends TelegramLongPollingBot {

    private final SendMessageService sendMessageService;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    public JobRadarTelegramBot(@Lazy SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            String chatId = update.getMessage().getChatId().toString();

            if (message.equals("/start")) { //  switch-case & enum?
                // Создание объекта StartCommand и передача значений
                StartCommand startCommand = new StartCommand(sendMessageService);
                startCommand.execute(update);
            }
            else if (message.equals("/stop")) {
                StopCommand stopCommand = new StopCommand(sendMessageService);
                stopCommand.execute(update);
            }
            else if (message.equals("/help")) {
                HelpCommand helpCommand = new HelpCommand(sendMessageService);
                helpCommand.execute(update);
            }
            else {
                NoCommand noCommand = new NoCommand(sendMessageService);
                noCommand.execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
