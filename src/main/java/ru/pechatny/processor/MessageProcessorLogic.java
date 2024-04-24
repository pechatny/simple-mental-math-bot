package ru.pechatny.processor;

import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pechatny.bot.Bot;
import ru.pechatny.command.StartCommand;

public class MessageProcessorLogic implements MessageProcessor {
    public static final String START_COMMAND = "/START";

    public MessageProcessorLogic() {
    }

    public void processMessage(Bot bot, Long chatId, String message) {
        message = message.trim();

        try {
            var command = new StartCommand(bot, chatId);
            command.execute();
        } catch (TelegramApiException e) {
            LoggerFactory.getLogger(MessageProcessorLogic.class).error("Message processing error! Message: {}", message);
        }
    }
}
