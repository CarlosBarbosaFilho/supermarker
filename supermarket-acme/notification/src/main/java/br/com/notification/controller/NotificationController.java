package br.com.notification.controller;

import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.controller.response.NotificationResponse;
import br.com.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apiguardian.api.API;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notification API")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @ResponseStatus( HttpStatus.CREATED)
    @PostMapping
    public void createNotification(@RequestBody NotificationRequest request) {
        this.service.createNotification(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> notifications() {
        return new ResponseEntity<List<NotificationResponse>>(this.service.list(), HttpStatus.OK);
    }
}
