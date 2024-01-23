package com.malog.member.infra.event;

import com.malog.blog.domain.event.BlogInitializedEvent;
import com.malog.member.domain.event.RegisterCompletedEvent;
import com.malog.member.domain.event.RegisteredAccountEvent;
import com.malog.notification.domain.event.RegisterCompletedNotiEvent;
import com.malog.notification.domain.event.RegisteredNotiEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserEventListener {
    private final ApplicationEventPublisher publisher;

    UserEventListener(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @EventListener
    public void registerEventListener(RegisteredAccountEvent event) {
        log.info("email : {} 회원 등록", event.getEmail());
        publisher.publishEvent(
            new RegisteredNotiEvent(event.getUKey(),
                event.getName(), event.getEmail(), event.getEmailCheckToken(),
                event.occurredOn()));
    }

    @EventListener
    public void registerEventListener(RegisterCompletedEvent event) {
        log.info("uKey : {} 회원 등록 완료", event.getuKey());
        publisher.publishEvent(new RegisterCompletedNotiEvent(event.getuKey(), event.occurredOn()));
        publisher.publishEvent(new BlogInitializedEvent(event.getuKey(), event.occurredOn()));
    }
}
