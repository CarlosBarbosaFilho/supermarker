package br.com.notification.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "tb_notification")
public class NotificationEntity {

    @Id
    @SequenceGenerator (
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )
    private Long id;

    private LocalDateTime sendAt;
    private Long idCustomer;
    private String message;
    private String sender;
    private String customer_email;
}
