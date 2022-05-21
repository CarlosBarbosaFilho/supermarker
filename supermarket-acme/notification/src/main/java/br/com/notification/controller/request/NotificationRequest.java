package br.com.notification.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

    private LocalDateTime sendAt;
    private Long idCustomer;
    private String message;
    private String sender;
    private String customer_email;
}
