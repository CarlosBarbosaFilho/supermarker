package br.com.notification.controller;

import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apiguardian.api.API;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notification")
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
}
