package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.NotificationDTO;
import org.BORDICO.Model.Inputs.NotificationInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationInput notificationInput) throws CustomException {
        NotificationDTO createdNotification = notificationService.createNotification(notificationInput);
        return ResponseEntity.ok(createdNotification);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<PageOutput<NotificationDTO>> getAllNotifications(PageInput pageInput) {
        PageOutput<NotificationDTO> notificationsPage = notificationService.getAllNotifications(pageInput);
        return ResponseEntity.ok(notificationsPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) throws CustomException {
        NotificationDTO notificationDTO = notificationService.getNotificationById(id);
        return ResponseEntity.ok(notificationDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<NotificationDTO> updateNotification(@PathVariable Long id, @RequestBody NotificationInput notificationInput) throws CustomException {
        NotificationDTO updatedNotification = notificationService.updateNotification(id, notificationInput);
        return ResponseEntity.ok(updatedNotification);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) throws CustomException {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok("Notification with ID " + id + " was deleted successfully");
    }
}