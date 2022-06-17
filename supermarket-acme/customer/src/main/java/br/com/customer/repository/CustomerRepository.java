package br.com.customer.repository;

import br.com.customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query ( "select c from CustomerEntity c where c.cpf = ?1" )
    String getCustomerEntitiesByCpf(String cpf);
}
