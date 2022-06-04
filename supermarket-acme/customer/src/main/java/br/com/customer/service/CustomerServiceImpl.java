package br.com.customer.service;


import br.com.clients.fraud.response.ClientFraudService;
import br.com.clients.notification.request.NotificationPayload;
import br.com.customer.config.ConvertUtils;
import br.com.customer.controller.request.CustomerRequest;
import br.com.customer.controller.response.CustomerResponse;
import br.com.customer.model.CustomerEntity;
import br.com.customer.repository.CustomerRepository;
import br.com.rabbitmq.RabbitMQMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl implements  CustomerService{

    private final CustomerRepository customerRepository;
    private final ConvertUtils convertUtils;
    private final ClientFraudService clientFraudService;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ConvertUtils convertUtils,
                               ClientFraudService clientFraudService,
                               RabbitMQMessageProducer rabbitMQMessageProducer) {
        this.customerRepository = customerRepository;
        this.convertUtils = convertUtils;
        this.clientFraudService = clientFraudService;
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        log.info("Calling the method to create customer {}", customerRequest);
        var customerEntity =
                (CustomerEntity) this.convertUtils.convertRequestToEntity(customerRequest, CustomerEntity.class);

        var entity = this.customerRepository.save(customerEntity);
        log.info(String.format("calling fraud service to customerId {}", entity.getId()));

//        var internalResponseFraud = this.clientFraudService.isFraud(entity.getId());
//
//        log.info(String.format("is fraud {}", internalResponseFraud.getIsFraud()));

        // send message to queue
        var notificationPayload = NotificationPayload
                .builder()
                .customer_email(entity.getEmail())
                .sender(entity.getName())
                .idCustomer(entity.getId())
                .message("Carlos send message to queue")
                .build();

        this.rabbitMQMessageProducer.publish(
                notificationPayload,
                "internal.exchange",
                "internal.notification.routing-key"
        );

        return (CustomerResponse) this.convertUtils.convertEntityToResponse(entity, CustomerResponse.class);
    }
}
