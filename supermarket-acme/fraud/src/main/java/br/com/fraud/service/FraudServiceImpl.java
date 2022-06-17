package br.com.fraud.service;

import br.com.fraud.config.ConvertUtils;
import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.model.FraudEntity;
import br.com.fraud.repository.FraudRepository;
import org.springframework.stereotype.Service;

@Service
public class FraudServiceImpl implements  FraudService{

    private final FraudRepository fraudRepository;
    private final ConvertUtils convertUtils;

    public FraudServiceImpl(FraudRepository fraudRepository, ConvertUtils convertUtils) {
        this.fraudRepository = fraudRepository;
        this.convertUtils = convertUtils;
    }

    @Override
    public FraudEntity isFraud(String cpf) {
        return this.fraudRepository.getFraudEntitiesByCustomerCpf(cpf);
    }

    @Override
    public FraudEntity registeredFraud(FraudRequest fraudRequest) {
       return this.fraudRepository.save((FraudEntity)
                this.convertUtils.convertRequestToEntity(fraudRequest, FraudEntity.class));
    }
}
