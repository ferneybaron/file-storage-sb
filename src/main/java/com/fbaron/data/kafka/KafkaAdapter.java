package com.fbaron.data.kafka;

import com.fbaron.core.model.FileUpload;
import com.fbaron.core.repository.SaveChunkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;

/**
 * @author Ferney Estupinan Baron
 * @since 12/29/2023
 */
@Slf4j
@RequiredArgsConstructor
public class KafkaAdapter implements SaveChunkRepository {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Saves a chunk of data at repository level.
     *
     * @param fileUpload The file to save the chunk to.
     */
    @Override
    public void saveChunk(FileUpload fileUpload) throws IOException {
        kafkaTemplate
                .send("file-storage-topic", fileUpload.getFileName(), fileUpload);
    }

}
