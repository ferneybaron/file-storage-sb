package com.fbaron.data.config;

import com.fbaron.core.repository.DeleteChunkRepository;
import com.fbaron.core.repository.FindChunkRepository;
import com.fbaron.core.repository.SaveChunkRepository;
import com.fbaron.data.kafka.KafkaAdapter;
import com.fbaron.data.local.LocalStorageAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author Ferney Estupinan Baron
 * @since 12/24/2023
 */
@Configuration
@RequiredArgsConstructor
public class StorageConfig {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Bean
    public SaveChunkRepository saveChunkRepository() {
        return new KafkaAdapter(kafkaTemplate);
    }

    @Bean
    public FindChunkRepository findChunkRepository() {
        return new LocalStorageAdapter();
    }

    @Bean
    public DeleteChunkRepository deleteChunkRepository() {
        return new LocalStorageAdapter();
    }

}
