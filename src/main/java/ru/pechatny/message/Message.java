package ru.pechatny.message;


import ru.pechatny.bot.Bot;
import ru.pechatny.processor.MessageProcessor;

public interface Message {
    void process(Bot bot, MessageProcessor processor);
}
