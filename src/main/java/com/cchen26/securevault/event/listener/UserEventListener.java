package com.cchen26.securevault.event.listener;

import com.cchen26.securevault.event.UserEvent;
import com.cchen26.securevault.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-06-22
 */

@Component
@RequiredArgsConstructor
public class UserEventListener {
    private final EmailService emailService;

    @EventListener
    public void onUserEvent(UserEvent event) {
        switch (event.getType()) {
            case REGISTRATION -> emailService.sendNewAccountEmail(event.getUser().getFirstName(), event.getUser().getEmail(), (String)event.getData().get("key"));
            case RESET_PASSWORD -> emailService.sendPasswordResetEmail(event.getUser().getFirstName(), event.getUser().getEmail(), (String)event.getData().get("key"));
            default -> {}
        }
    }
}
