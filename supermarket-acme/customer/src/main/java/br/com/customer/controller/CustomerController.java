package br.com.customer.controller;

import br.com.customer.controller.request.CustomerRequest;
import br.com.customer.controller.response.CustomerResponse;
import br.com.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ( "/api/v1/customers" )
@Tag ( name = "Api to management customers", description = "Create and management customers" )
public class CustomerController {

    private final CustomerService customerService;

    private Logger logger = LogManager.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation ( summary = "create customer", description = "create customer to fraud system" )
    @ApiResponse ( responseCode = "201", description = "Customer success created" )
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest) {
        logger.trace("Customer CPF: {}", customerRequest.getCpf());
        return ResponseEntity.ok(this.customerService.createCustomer(customerRequest));
    }

    @Operation ( summary = "list customer", description = "list all customers" )
    @ApiResponse ( responseCode = "200", description = "Customer listed with success" )
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> listCustomers() {
        return ResponseEntity.ok(this.customerService.listCustomers());
    }
}

