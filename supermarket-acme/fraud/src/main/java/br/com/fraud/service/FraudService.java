package br.com.fraud.service;

import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.model.FraudEntity;

public interface FraudService {

    FraudEntity isFraud(String cpf);

    FraudEntity registeredFraud(FraudRequest fraudRequest);
}
