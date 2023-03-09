package org.playbox.services;

import io.minio.DownloadObjectArgs;
import io.minio.ListObjectsArgs;
import io.minio.Result;
import org.playbox.Server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourcesDownloader {
    public static void fromBucket(String bucketName, String folder) {
        // Getting bucket information (and all bucket files)
        var results = MinioInstance.CLIENT.listObjects(
                ListObjectsArgs.builder()
                        .recursive(true)
                        .bucket(bucketName)
                        .build()
        );

        Server.LOGGER.info(String.format("Got bucket objects: %s", results));
        results.forEach(item -> {
            try {
                var object = item.get();
                if (object.isDir()) return;

                String objectPath = folder + object.objectName();

                // Creating directory structure
                createNestedDirectoryStructure(objectPath);

                // Trying to download this object
                Server.LOGGER.info(String.format("Got object %s, downloading to: %s", object.objectName(), objectPath));

                MinioInstance.CLIENT.downloadObject(
                        DownloadObjectArgs.builder()
                                .bucket(bucketName)
                                .object(object.objectName())
                                .overwrite(true)
                                .filename(objectPath)
                                .build()
                );
            } catch(Throwable error) {
                Server.LOGGER.error(String.format("Error while getting object from %s bucket:", bucketName));
                Server.LOGGER.error("Error:", error);
            };
        });
    };

    private static void createNestedDirectoryStructure(String objectPath) throws IOException {
        Path path = Paths.get(objectPath);
        Files.createDirectories(path);
    };
}
