package br.com.customer.service;


import br.com.clients.fraud.response.ClientFraudService;
import br.com.clients.notification.request.NotificationPayload;
import br.com.customer.config.ConvertUtils;
import br.com.customer.controller.CustomerController;
import br.com.customer.controller.request.CustomerRequest;
import br.com.customer.controller.response.CustomerResponse;
import br.com.customer.model.CustomerEntity;
import br.com.customer.repository.CustomerRepository;
import br.com.customer.service.exception.CustomerFraudException;
import br.com.rabbitmq.RabbitMQMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements  CustomerService{

    private Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

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
    public String createCustomer(CustomerRequest customerRequest) {

        var internalResponseFraud = this.clientFraudService.isFraud(customerRequest.getCpf());

        if (internalResponseFraud != null) {

            logger.trace("Calling service CPF: {}", customerRequest.getCpf());
            // send message to queue
            var notificationPayload = NotificationPayload
                    .builder()
                    .customer_email(customerRequest.getEmail())
                    .sender(customerRequest.getName())
                    .customer_cpf(customerRequest.getCpf())
                    .message(customerRequest.getName())
                    .build();

            this.rabbitMQMessageProducer.publish(
                    notificationPayload,
                    "internal.exchange",
                    "internal.notification.routing-key"
            );

            logger.trace("The customer is a fraud, CPF {}", customerRequest.getCpf());
           return ("The customer is a fraud, cpf :" + customerRequest.getCpf());

        } else {
            logger.trace("Calling the method to create customer {}", customerRequest);
            var customerEntity =
                    (CustomerEntity) this.convertUtils.convertRequestToEntity(customerRequest, CustomerEntity.class);

            var entity = this.customerRepository.save(customerEntity);
            logger.trace("calling fraud service to customerId {}", entity.getCpf());

            this.convertUtils.convertEntityToResponse(entity, CustomerResponse.class);

            return "Customer created with success";
        }

    }

    @Override
    public List<CustomerResponse> listCustomers() {
        return this.convertUtils.convertToListResponse(this.customerRepository.findAll(), CustomerResponse.class);
    }
}
