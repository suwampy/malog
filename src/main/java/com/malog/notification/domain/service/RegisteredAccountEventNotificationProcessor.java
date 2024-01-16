package com.malog.notification.domain.service;

import com.malog.notification.domain.Notification;
import com.malog.notification.domain.NotificationGroupType;
import com.malog.notification.domain.Receiver;
import com.malog.notification.domain.event.RegisteredNotiEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisteredAccountEventNotificationProcessor implements
    NotificationEventProcessor<RegisteredNotiEvent> {

    @Override
    public Class<RegisteredNotiEvent> appliesTo() {
        return RegisteredNotiEvent.class;
    }

    @Override
    public Notification process(RegisteredNotiEvent event) {
        String mailAddress = event.email();
        String userName = event.userName();
        String message = userName + "님,"+ mailAddress +"로 발송된 인증 토큰으로 인증을 완료해주세요.";

        return new Notification(new Receiver(event.accountId()), message, NotificationGroupType.SYSTEM);
    }
}
