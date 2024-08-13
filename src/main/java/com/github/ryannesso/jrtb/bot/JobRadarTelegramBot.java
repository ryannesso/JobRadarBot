package com.github.ryannesso.jrtb.bot;

import com.github.ryannesso.jrtb.command.CommandContainer;
import com.github.ryannesso.jrtb.service.SendMessageServiceImpl;
import com.github.ryannesso.jrtb.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public JobRadarTelegramBot(TelegramUserService telegramUserService) {
        this.commandContainer = new CommandContainer(new SendMessageServiceImpl(this), telegramUserService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith("/")) {
                String commandInd = message.split(" ")[0].toLowerCase(); // Ensure commandInd is correctly formatted

                commandContainer.retrieveCommand(commandInd).execute(update); // Fixed method name
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update); // Fixed method name
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
