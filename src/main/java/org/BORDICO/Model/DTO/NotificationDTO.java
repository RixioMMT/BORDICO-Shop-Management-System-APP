package org.BORDICO.Model.DTO;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long Id;
    private String notificationName;
    private String notificationDescription;
    private Long userId;
}
