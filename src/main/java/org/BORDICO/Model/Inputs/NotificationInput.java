package org.BORDICO.Model.Inputs;

import lombok.Data;
import org.BORDICO.Model.Entity.User;

@Data
public class NotificationInput {
    private String notificationName;
    private String notificationDescription;
    private Long userId;
}
