package ru.pechatny.message;


import ru.pechatny.bot.Bot;
import ru.pechatny.processor.MessageProcessor;

public class MsgBase implements Message {
    private final Long chatId;
    private final String message;

    public MsgBase(Long chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }

    @Override
    public void process(Bot bot, MessageProcessor processor) {
        processor.processMessage(bot, chatId, message);
    }
}
