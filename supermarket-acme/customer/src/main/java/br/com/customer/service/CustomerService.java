package br.com.customer.service;

import br.com.customer.controller.CustomerController;
import br.com.customer.controller.request.CustomerRequest;
import br.com.customer.controller.response.CustomerResponse;
import br.com.customer.model.CustomerEntity;

import java.util.List;

public interface CustomerService {

    String createCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> listCustomers();
}
