package com.github.ryannesso.jrtb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.github.ryannesso.jrtb.bot.JobRadarTelegramBot;

@Service
public class SendMessageServiceImpl implements SendMessageService {
    private final JobRadarTelegramBot jobradarbot;

    @Autowired
    public SendMessageServiceImpl(JobRadarTelegramBot jobradarbot) {
        this.jobradarbot = jobradarbot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            jobradarbot.execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
