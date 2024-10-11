package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;
    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    public String uploadFileToS3(String directory, String fileName, byte[] fileData) {
        String fileKey = directory + "/" + URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKey)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileData));
        return "https://" + bucketName + ".s3.amazonaws.com/" + fileKey;
    }
}