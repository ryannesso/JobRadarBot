package com.github.ryannesso.jrtb.service;
/**
 * Service for sending messages via telegram-bot.
 */
public interface SendMessageService {

    /**
     * Send message via telegram bot.
     *
     * @param chatId provided chatId in which messages would be sent.
     * @param message provided message to be sent.
     */

    void sendMessage(String chatId, String message);
}