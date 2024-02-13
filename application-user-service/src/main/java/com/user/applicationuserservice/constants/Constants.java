package com.user.applicationuserservice.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String WELCOME_MESSAGE;

    @Value("${greeting.message}")
    public void setWelcomeMessage(String welcomeMessage) {
        WELCOME_MESSAGE = welcomeMessage;
    }
}
