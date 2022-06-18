package br.com.notification.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {

    private Long id;
    private String cpf_customer;
    private String message;
    private String sender;
    private String customer_email;
}
