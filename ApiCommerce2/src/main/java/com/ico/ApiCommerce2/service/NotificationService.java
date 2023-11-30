package com.ico.ApiCommerce2.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.ico.ApiCommerce2.controller.authentication.AuthenticationClientController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void sendNotification(String token, String messageBody) {
        Message message = Message.builder()
                .putData("message", messageBody)
                .setToken(token)
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
            logger.info(message.toString());

        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les erreurs d'envoi de notification
        }
    }
}
