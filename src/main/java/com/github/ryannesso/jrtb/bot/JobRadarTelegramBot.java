package com.github.ryannesso.jrtb.bot;

import com.github.ryannesso.jrtb.command.CommandContainer;
import com.github.ryannesso.jrtb.service.SendMessageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.ryannesso.jrtb.command.CommandName.NO;

@Component
public class JobRadarTelegramBot extends TelegramLongPollingBot {

    private final CommandContainer commandContainer;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    public JobRadarTelegramBot() {
        this.commandContainer = new CommandContainer(new SendMessageServiceImpl(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if(message.startsWith("/")) {
                String commandInd = message.split(" ")[0].toLowerCase();

                commandContainer.retriveCommand(commandInd).execute(update);
            }
            else {
                commandContainer.retriveCommand(NO.getCommandName()).execute(update);
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
