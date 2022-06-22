package br.com.notification.consumer;

import br.com.clients.notification.request.NotificationPayload;
import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.model.NotificationEntity;
import br.com.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationPayload notificationResponseMessage) {
        this.notificationService.createNotification(convertPayloadToNotification(notificationResponseMessage));
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    private NotificationRequest convertPayloadToNotification(NotificationPayload notificationPayload) {

        var request = new NotificationRequest();
        request.setCpf_customer(notificationPayload.getCustomer_cpf());
        request.setCustomer_email(notificationPayload.getCustomer_email());
        request.setMessage(notificationPayload.getMessage());
        request.setSender(notificationPayload.getSender());
        return request;
    }
}
