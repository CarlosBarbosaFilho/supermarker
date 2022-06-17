package br.com.fraud.controller;

import br.com.fraud.config.ConvertUtils;
import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;
import br.com.fraud.model.FraudEntity;
import br.com.fraud.service.FraudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/frauds")
public class FraudController {

    private final FraudService fraudService;
    private final ConvertUtils convertUtils;

    public FraudController(FraudService fraudService, ConvertUtils convertUtils) {
        this.fraudService = fraudService;
        this.convertUtils = convertUtils;
    }

    @GetMapping("/is-fraud/{cpf}")
    public ResponseEntity<FraudResponse> isFraud(@PathVariable("cpf") String cpf) {
        var response = this.fraudService.isFraud(cpf);
        return ResponseEntity.ok((FraudResponse) convertUtils.convertEntityToResponse(response, FraudResponse.class));
    }

    @PostMapping("/registry-fraud")
    public ResponseEntity<FraudResponse> fraudRegistry(@RequestBody FraudRequest fraudRequest) {
        return ResponseEntity
                .ok((FraudResponse) convertUtils
                        .convertEntityToResponse(this.fraudService.registeredFraud(fraudRequest), FraudResponse.class));
    }
}
