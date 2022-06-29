package br.com.fraud.service;

import br.com.fraud.config.ConvertUtils;
import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;
import br.com.fraud.model.FraudEntity;
import br.com.fraud.repository.FraudRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraudServiceImpl implements FraudService {

    private final FraudRepository fraudRepository;
    private final ConvertUtils convertUtils;

    public FraudServiceImpl(FraudRepository fraudRepository, ConvertUtils convertUtils) {
        this.fraudRepository = fraudRepository;
        this.convertUtils = convertUtils;
    }

    @Override
    @Cacheable ( "is-fraud" )
    public FraudEntity isFraud(String cpf) {
        System.out.println("cache: verify there is using cache on is fraud");
        return this.fraudRepository.getFraudEntitiesByCustomerCpf(cpf);
    }

    @Override
    public FraudEntity registeredFraud(FraudRequest fraudRequest) {
        return this.fraudRepository.save((FraudEntity)
                this.convertUtils.convertRequestToEntity(fraudRequest, FraudEntity.class));
    }

    @Override
    @Cacheable ( "list-frauds-customers" )
    public List<FraudEntity> listFrauds() {
        System.out.println("chache: list of frauds");
        return this.fraudRepository.findAll();
    }
}
