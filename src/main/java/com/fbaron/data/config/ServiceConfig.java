package com.fbaron.data.config;

import com.fbaron.core.domain.DomainUploadChunkService;
import com.fbaron.core.repository.DeleteChunkRepository;
import com.fbaron.core.repository.FindChunkRepository;
import com.fbaron.core.repository.SaveChunkRepository;
import com.fbaron.core.usecase.UploadChunkUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ferney Estupinan Baron
 * @since 12/29/2023
 */

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {

    private final SaveChunkRepository saveChunkRepository;
    private final FindChunkRepository findChunkRepository;
    private final DeleteChunkRepository deleteChunkRepository;

    @Bean
    public UploadChunkUseCase uploadChunkUseCase() {
        //return new DomainFileStorageService(saveChunkRepository, findChunkRepository, deleteChunkRepository);
        return new DomainUploadChunkService(saveChunkRepository);
    }

}
