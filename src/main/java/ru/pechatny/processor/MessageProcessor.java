package ru.pechatny.processor;


import ru.pechatny.bot.Bot;

public interface MessageProcessor {
    void processMessage(Bot bot, Long chatId, String message);
}
