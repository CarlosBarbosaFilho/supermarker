package br.com.notification.service;

import br.com.notification.controller.request.NotificationRequest;

public interface NotificationService {

    void createNotification(NotificationRequest request);
}
