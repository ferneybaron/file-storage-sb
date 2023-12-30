package com.fbaron.core.domain;


import com.fbaron.core.model.FileUpload;
import com.fbaron.core.repository.DeleteChunkRepository;
import com.fbaron.core.repository.FindChunkRepository;
import com.fbaron.core.repository.SaveChunkRepository;
import com.fbaron.core.usecase.CombineChunksUseCase;
import com.fbaron.core.usecase.UploadChunkUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Ferney Estupinan Baron
 * @since 12/24/2023
 */

@Slf4j
@RequiredArgsConstructor
public class DomainFileStorageService implements UploadChunkUseCase, CombineChunksUseCase {

    private final SaveChunkRepository saveChunkRepository;
    private final FindChunkRepository findChunkRepository;
    private final DeleteChunkRepository deleteChunkRepository;

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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (chunkNumber == totalChunks - 1) {
            var combined = combineChunks(totalChunks, fileName, originalFileHash);

            try {
                String actualHash = calculateInputStreamHash(combined);

                if (originalFileHash.equals(actualHash)) {
                    log.info("InputStream hash verification successful.");
                } else {
                    log.error("InputStream hash verification failed. Expected: " + originalFileHash + ", Actual: " + actualHash);
                }
                return "All Chunks were combined into a single file: " + fileName;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "Chunk " + chunkNumber + " received";
    }

    /**
     * Combines chunks of a file into a single file.
     *
     * @param totalChunks      The total number of chunks.
     * @param fileName         The name of the file.
     * @param originalFileHash The hash of the original file.
     * @return The combined file as an InputStream.
     */
    @Override
    public InputStream combineChunks(Integer totalChunks, String fileName, String originalFileHash) {
        Path completeFilePath = Paths.get("temp", fileName);
        try (OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(completeFilePath))) {
            for (int i = 0; i < totalChunks; i++) {
                var chunk = findChunkRepository.findChunkById(fileName, i);
                outputStream.write(chunk);
                deleteChunkRepository.deleteChunkById(fileName, i);
            }
            return Files.newInputStream(completeFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String calculateInputStreamHash(InputStream inputStream) throws IOException {
        try {
            return DigestUtils.sha256Hex(inputStream);
        } catch (Exception e) {
            throw new IOException("Error calculating InputStream hash", e);
        }
    }

}