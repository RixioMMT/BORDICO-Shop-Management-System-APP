package org.BORDICO.Service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.Entity.Notification;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.nio.charset.StandardCharsets;

@Service
@Builder
@RequiredArgsConstructor
public class LambdaService {
    private final LambdaClient lambdaClient;
    public void invokeUserCreatedNotification(Notification notification) {
        String payload = String.format("{ \"notificationName\": \"%s\", \"notificationDescription\": \"%s\", \"userId\": \"%d\" }",
                notification.getNotificationName(),
                notification.getNotificationDescription(),
                notification.getUser().getId());
        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName("UserCreatedNotification")
                .payload(SdkBytes.fromString(payload, StandardCharsets.UTF_8))
                .build();
        InvokeResponse result = lambdaClient.invoke(invokeRequest);
        String response = result.payload().asUtf8String();
        System.out.println("Lambda response: " + response);
        System.out.println("Lambda Status Code: " + result.statusCode());
    }
}
