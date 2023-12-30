package com.fbaron.core.domain;


import com.fbaron.core.model.FileUpload;
import com.fbaron.core.repository.SaveChunkRepository;
import com.fbaron.core.usecase.UploadChunkUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Ferney Estupinan Baron
 * @since 12/24/2023
 */

@Slf4j
@RequiredArgsConstructor
public class DomainUploadChunkService implements UploadChunkUseCase {

    private final SaveChunkRepository saveChunkRepository;

    /**
     * Uploads a chunk of data to the specified file.
     *
     * @param file             The file to upload the chunk to.
     * @param chunkNumber      The number of the chunk being uploaded.
     * @param totalChunks      The total number of chunks being uploaded.
     * @param fileName         The name of the file being uploaded.
     * @param originalFileHash The hash of the original file.
     * @param contentRange     The content range of the chunk being uploaded.
     * @return The result of the upload.
     */
    @Override
    public String uploadChunk(MultipartFile file, Integer chunkNumber, Integer totalChunks, String fileName,
                              String originalFileHash, String contentRange) {
        try {
            byte[] fileBytes = file.getBytes();
            FileUpload fileUpload = FileUpload.builder()
                    .fileName(fileName)
                    .chunkNumber(chunkNumber)
                    .file(fileBytes)
                    .totalChunks(totalChunks)
                    .originalFileHash(originalFileHash)
                    .contentRange(contentRange)
                    .build();

            saveChunkRepository.saveChunk(fileUpload);
            return "Chunk " + chunkNumber + " received";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}