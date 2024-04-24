package ru.pechatny.bot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotConfigurationException extends Exception{
    public BotConfigurationException(TelegramApiException e) {
        super(e);
    }
}
