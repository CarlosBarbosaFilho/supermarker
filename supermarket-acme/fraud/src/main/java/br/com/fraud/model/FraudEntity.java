package br.com.fraud.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table ( name = "tb_fraud" )
public class FraudEntity implements Serializable {

    @Id
    @SequenceGenerator (
            name = "fraud_id_sequence",
            sequenceName = "fraud_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_sequence"
    )
    private Long id;

    private String description;

    private String customerCpf;

    private boolean isFraud = true;

    private LocalDateTime createAt = LocalDateTime.now();
}
