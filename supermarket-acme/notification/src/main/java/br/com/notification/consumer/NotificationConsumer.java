package br.com.notification.consumer;

import br.com.clients.notification.request.NotificationPayload;
import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.model.NotificationEntity;
import br.com.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationPayload notificationResponseMessage) {
        log.info("Consumed {} from queue", notificationResponseMessage);
        this.notificationService.createNotification(convertPayloadToNotification(notificationResponseMessage));
    }

    private NotificationRequest convertPayloadToNotification(NotificationPayload notificationPayload) {
//        return NotificationRequest.builder()
//                .customer_email(notificationPayload.getCustomer_email())
//                .cpf_customer(notificationPayload.getCustomer_cpf())
//                .sender(notificationPayload.getSender())
//                .message(notificationPayload.getMessage())
//                .build();

        var request = new NotificationRequest();
        request.setCpf_customer(notificationPayload.getCustomer_cpf());
        request.setCustomer_email(notificationPayload.getCustomer_email());
        request.setMessage(notificationPayload.getMessage());
        request.setSender(notificationPayload.getSender());
        return request;
    }
}
