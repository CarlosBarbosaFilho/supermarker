package br.com.clients.notification.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationPayload {

    @JsonProperty("sender")
    private String sender;
    @JsonProperty("customer_email")
    private String customer_email;
    @JsonProperty("customer_cpf")
    private String customer_cpf;
    @JsonProperty("message")
    private String message;
}
