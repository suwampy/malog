package com.malog.notification.application.event;

import com.malog.common.domain.DomainEvent;
import com.malog.notification.domain.Notification;
import com.malog.notification.domain.service.NotificationEventProcessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public final class NotificationEventProcessDelegator {
    private final List<NotificationEventProcessor> processors;

    private Map<Class<? extends DomainEvent>, NotificationEventProcessor> processorMap =
        new HashMap<>();

    public NotificationEventProcessDelegator(
        List<NotificationEventProcessor> processors) {
        this.processors = processors;
        setProcessorMap(this.processors);
    }

    private void setProcessorMap(List<NotificationEventProcessor> processors) {
        for (NotificationEventProcessor processor : processors) {
            processorMap.put(processor.appliesTo(), processor);
        }
    }

    public Notification process(DomainEvent event) {
        var processor = Objects.requireNonNull(
            processorMap.get(event.getClass()),
            "도메인 이벤트 타입이 명확하지 않습니다.");
        return processor.process(event);
    }
}
