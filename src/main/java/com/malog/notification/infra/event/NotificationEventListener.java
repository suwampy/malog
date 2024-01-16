package com.malog.notification.infra.event;


import com.malog.notification.domain.NotificationRepository;
import com.malog.notification.application.event.NotificationEventProcessDelegator;
import com.malog.notification.domain.event.RegisterCompletedNotiEvent;
import com.malog.notification.domain.event.RegisteredNotiEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationEventListener {

    private final NotificationRepository notificationRepository;
    private final NotificationEventProcessDelegator processDelegator;


    @EventListener
    @Async
    public void handle(RegisteredNotiEvent event) {
        notificationRepository.save(processDelegator.process(event));
        // 이메일 전송 로직
        log.info("noti 등록");
    }

    @EventListener
    @Async
    public void handle(RegisterCompletedNotiEvent event) {
        notificationRepository.save(processDelegator.process(event));
        log.info("noti 등록");
    }
}
