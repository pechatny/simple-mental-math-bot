package ru.pechatny.processor;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import ru.pechatny.bot.Bot;

public class MessageProcessorMetric implements MessageProcessor {
    public static final String METRIC_NAME = "simple.mental.math.bot.messages.counter";
    private final MessageProcessor processor;
    private final Counter counter;

    public MessageProcessorMetric(MessageProcessor processor, MeterRegistry meterRegistry) {
        this.counter = Counter
            .builder(METRIC_NAME)
            .register(meterRegistry);
        this.processor = processor;
    }

    @Override
    public void processMessage(Bot bot, Long chatId, String message) {
        counter.increment();
        processor.processMessage(bot, chatId, message);
    }
}
