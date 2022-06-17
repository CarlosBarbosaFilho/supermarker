package br.com.fraud.repository;

import br.com.fraud.model.FraudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudRepository extends JpaRepository<FraudEntity, Long> {


    @Query ( "select f from FraudEntity f where f.customerCpf = ?1" )
    FraudEntity getFraudEntitiesByCustomerCpf(String cpf);
}
