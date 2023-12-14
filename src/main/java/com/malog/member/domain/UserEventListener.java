package com.malog.member.domain;

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
    public void registerEventListener(RegisteredAccountEvent registeredAccountEvent) {
        log.info("email : {} 회원 등록", registeredAccountEvent.getEmail());
    }

    @EventListener
    public void registerEventListener(RegisterCompletedEvent registerCompletedEvent) {
        log.info("uKey : {} 회원 등록 완료", registerCompletedEvent.getuKey());
    }
}
