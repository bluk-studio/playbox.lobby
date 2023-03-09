package org.playbox.services;

import io.minio.MinioClient;

public class MinioInstance {
    public static final MinioClient CLIENT;

    static {
        CLIENT = MinioClient.builder()
                .endpoint("https://assets.k8s.odzi.dog")
                .build();
    }
}