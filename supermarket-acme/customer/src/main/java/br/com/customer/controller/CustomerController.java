package br.com.customer.controller;

import br.com.customer.controller.request.CustomerRequest;
import br.com.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Api to management customers", description = "Create and management customers")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "create customer", description = "create customer to fraud system")
    @ApiResponse(responseCode = "201", description = "Customer success created")
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("calling controller to create customer {}", customerRequest );
        return ResponseEntity.ok(this.customerService.createCustomer(customerRequest));
    }
}

