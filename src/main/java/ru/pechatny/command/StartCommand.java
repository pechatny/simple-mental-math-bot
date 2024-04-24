package ru.pechatny.command;

import ru.pechatny.bot.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand implements Command{
    private static final String START_MESSAGE = "start message text";
    private final Bot bot;
    private final Long chatId;

    public StartCommand(Bot bot, Long chatId) {
        this.bot = bot;
        this.chatId = chatId;
    }


    @Override
    public void execute() throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setText(START_MESSAGE);
        sendMessage.setChatId(chatId);
        bot.execute(sendMessage);

    }
}
