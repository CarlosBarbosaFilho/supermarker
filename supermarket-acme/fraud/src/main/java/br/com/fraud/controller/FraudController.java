package br.com.fraud.controller;

import br.com.fraud.config.ConvertUtils;
import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;
import br.com.fraud.exception.CPFNotFoundException;
import br.com.fraud.model.FraudEntity;
import br.com.fraud.service.FraudService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( "/api/v1/frauds" )
public class FraudController {

    private final FraudService fraudService;
    private final ConvertUtils convertUtils;
    private Logger logger = LogManager.getLogger(FraudController.class);

    public FraudController(FraudService fraudService, ConvertUtils convertUtils) {
        this.fraudService = fraudService;
        this.convertUtils = convertUtils;
    }

    @GetMapping ( "/is-fraud/{cpf}" )
    public ResponseEntity<FraudResponse> isFraud(@PathVariable ( "cpf" ) String cpf) {
        var response = this.fraudService.isFraud(cpf);
        if (response == null) {
            logger.trace("Document not found {}", cpf);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok((FraudResponse) convertUtils.convertEntityToResponse(response, FraudResponse.class));
        }
    }

    @PostMapping ( "/registry-fraud" )
    public ResponseEntity<FraudResponse> fraudRegistry(@RequestBody FraudRequest fraudRequest) {
        return ResponseEntity
                .ok((FraudResponse) convertUtils
                        .convertEntityToResponse(this.fraudService.registeredFraud(fraudRequest), FraudResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<FraudResponse>> list() {
        return ResponseEntity.ok((List<FraudResponse>) convertUtils.convertToListResponse(this.fraudService.listFrauds(), FraudResponse.class));
    }
}
