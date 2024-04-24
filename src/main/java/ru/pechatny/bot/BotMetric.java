package ru.pechatny.bot;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

@Primary
@Component
public class BotMetric implements LongPollingBot {
    private final LongPollingBot bot;
    private final Counter counter;

    BotMetric(@Qualifier("processor") LongPollingBot bot, MeterRegistry meterRegistry) {
        this.bot = bot;
        this.counter = Counter.builder("congratulation.manager.bot.errors.counter").register(meterRegistry);
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            bot.onUpdateReceived(update);
        } catch (Exception e){
            counter.increment();
            throw e;
        }
    }

    @Override
    public BotOptions getOptions() {
        return bot.getOptions();
    }

    @Override
    public void clearWebhook() throws TelegramApiRequestException {
        bot.clearWebhook();
    }

    @Override
    public String getBotUsername() {
        return bot.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return bot.getBotToken();
    }
}
