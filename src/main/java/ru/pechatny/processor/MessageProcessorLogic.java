package ru.pechatny.processor;

import ru.pechatny.bot.Bot;
import ru.pechatny.command.StartCommand;

public class MessageProcessorLogic implements MessageProcessor {
    public static final String START_COMMAND = "/START";

    public MessageProcessorLogic() {
    }

    public void processMessage(Bot bot, Long chatId, String message) {
        var command = new StartCommand(bot, chatId);
        command.execute();
    }
}
