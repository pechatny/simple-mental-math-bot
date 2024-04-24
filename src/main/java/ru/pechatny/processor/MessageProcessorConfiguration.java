package ru.pechatny.processor;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageProcessorConfiguration {
    private final MeterRegistry meterRegistry;

    public MessageProcessorConfiguration(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Bean
    MessageProcessor messageProcessor() {
        return new MessageProcessorMetric(
                new MessageProcessorLogic(),
                meterRegistry
        );
    }
}
