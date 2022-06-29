package br.com.notification.service;

import br.com.notification.config.ConvertUtils;
import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.controller.response.NotificationResponse;
import br.com.notification.model.NotificationEntity;
import br.com.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ConvertUtils convertUtils;

    public NotificationServiceImpl(NotificationRepository notificationRepository, ConvertUtils convertUtils) {
        this.notificationRepository = notificationRepository;
        this.convertUtils = convertUtils;
    }

    @Override
    public void createNotification(NotificationRequest request) {
        this.notificationRepository.save((NotificationEntity) convertUtils.convertRequestToEntity(request, NotificationEntity.class));
    }

    @Override
    public List<NotificationResponse> list() {
        return convertUtils.convertToListResponse(this.notificationRepository.findAll(), NotificationResponse.class);
    }
}
