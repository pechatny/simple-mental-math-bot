package ru.pechatny.bot;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfiguration {
    private final LongPollingBot bot;

    public BotConfiguration(LongPollingBot bot) {
        this.bot = bot;
    }

    @PostConstruct
    public void registerBot() throws BotConfigurationException {
        try {
            var botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            throw new BotConfigurationException(e);
        }
    }
}
