package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.NotificationDTO;
import org.BORDICO.Model.Entity.Notification;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.NotificationInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.NotificationRepository;
import org.BORDICO.Repository.UserRepository;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    private final UserRepository userRepository;
    public NotificationDTO createNotification(NotificationInput notificationInput) throws CustomException {
        Notification notification = new Notification();
        return getNotificationFromInput(notificationInput, notification);
    }
    public PageOutput<NotificationDTO> getAllNotifications(PageInput pageInput) {
        Page<Notification> notifications = notificationRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                notifications.getNumber(),
                notifications.getTotalPages(),
                notifications.getTotalElements(),
                notifications.getContent().stream()
                        .map(notification -> modelMapperUtil.map(notification, NotificationDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public NotificationDTO getNotificationById(Long id) throws CustomException {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new CustomException("Notification with ID " + id + " not found"));
        return modelMapper.map(notification, NotificationDTO.class);
    }
    public NotificationDTO updateNotification(Long id, NotificationInput notificationInput) throws CustomException {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new CustomException("Notification with ID " + id + " not found"));
        return getNotificationFromInput(notificationInput, notification);
    }
    public void deleteNotification(Long id) throws CustomException {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new CustomException("Notification with ID " + id + " not found"));
        notificationRepository.delete(notification);
    }
    private NotificationDTO getNotificationFromInput(NotificationInput notificationInput, Notification notification) throws CustomException {
        User user = userRepository.findById(notificationInput.getUserId())
                .orElseThrow(() -> new CustomException("User with ID " + notificationInput.getUserId() + " not found"));
        notification.setNotificationName(notificationInput.getNotificationName());
        notification.setNotificationDescription(notificationInput.getNotificationDescription());
        notification.setUser(user);
        notification = notificationRepository.save(notification);
        return modelMapper.map(notification, NotificationDTO.class);
    }
}