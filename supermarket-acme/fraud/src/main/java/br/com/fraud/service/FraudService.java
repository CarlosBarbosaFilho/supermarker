package br.com.fraud.service;

import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;
import br.com.fraud.model.FraudEntity;

import java.util.List;

public interface FraudService {

    FraudEntity isFraud(String cpf);

    FraudEntity registeredFraud(FraudRequest fraudRequest);

    List<FraudEntity> listFrauds();
}
