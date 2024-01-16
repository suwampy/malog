package com.malog.notification.domain.service;

import com.malog.member.domain.User;
import com.malog.member.domain.UserRepository;
import com.malog.notification.domain.Notification;
import com.malog.notification.domain.NotificationGroupType;
import com.malog.notification.domain.Receiver;
import com.malog.notification.domain.event.RegisterCompletedNotiEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisteredCompletedNotiEventProcessor implements
    NotificationEventProcessor<RegisterCompletedNotiEvent> {
    private final UserRepository userRepository;

    @Override
    public Class<RegisterCompletedNotiEvent> appliesTo() {
        return RegisterCompletedNotiEvent.class;
    }

    @Override
    public Notification process(RegisterCompletedNotiEvent event) {
        User user = userRepository.findByUniqueKey(event.accountId());
        String notiMsg = user.getName()+ "님, 가입해주셔서 감사합니다.";

        return new Notification(new Receiver(event.accountId()), notiMsg, NotificationGroupType.SYSTEM);
    }
}
