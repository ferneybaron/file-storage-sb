package com.fbaron;

import com.fbaron.core.model.FileUpload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * @author Ferney Estupinan Baron
 * @since 12/22/2023
 */
@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @KafkaListener(topics = "file-storage-topic", groupId = "file-storage")
    public void listenGroupFileStorage(FileUpload fileUpload) throws IOException {
        log.info("Received Message in group file-storage: " + fileUpload.getFileName());
        Path tempDirPath = Path.of("temp");
        Files.createDirectories(tempDirPath);

        Path chunkFilePath = tempDirPath
                .resolve(fileUpload.getFileName() + "_chunk" + fileUpload.getChunkNumber());
        Files.write(chunkFilePath, fileUpload.getFile());
    }

}
