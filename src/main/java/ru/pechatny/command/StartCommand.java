package ru.pechatny.command;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pechatny.bot.Bot;

import java.util.Random;
import java.util.stream.IntStream;

public class StartCommand implements Command {
    public static final int WAIT_BETWEEN_QUESTIONS = 4000;
    public static final int WAIT_AFTER_ANSWER = 500;
    private final Bot bot;
    private final Long chatId;

    public StartCommand(Bot bot, Long chatId) {
        this.bot = bot;
        this.chatId = chatId;
    }


    @Override
    public void execute() {
        sendMessage(chatId, "Начало упражнения!");
        Random random = new Random();
        IntStream.rangeClosed(1, 10).forEach(i -> {
            int a = random.nextInt(11, 100);
            int b = random.nextInt(11,100);
            int result = a + b;
            String task = "%d: %d + %d".formatted(i, a, b);
            sendMessage(chatId, task);
            try {
                Thread.sleep(WAIT_BETWEEN_QUESTIONS);
                String resultMessage = "*%d*".formatted(result);
                sendMessage(chatId, resultMessage);

                Thread.sleep(WAIT_AFTER_ANSWER);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        sendMessage(chatId, "Упражнение закончено!");
    }

    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.disableNotification();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        try {
            this.bot.execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }
}
