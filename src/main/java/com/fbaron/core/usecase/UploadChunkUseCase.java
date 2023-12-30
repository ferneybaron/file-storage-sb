package com.fbaron.core.usecase;

import org.springframework.web.multipart.MultipartFile;

/**
 * The UploadChunkUseCase interface represents the use case for uploading a chunk of data.
 *
 * @author Ferney Estupinan Baron
 * @since 12/24/2023
 */
public interface UploadChunkUseCase {

    /**
     * Uploads a chunk of data to the specified file.
     *
     * @param file The file to upload the chunk to.
     * @param chunkNumber The number of the chunk being uploaded.
     * @param totalChunks The total number of chunks being uploaded.
     * @param fileName The name of the file being uploaded.
     * @param originalFileHash The hash of the original file.
     * @param contentRange The content range of the chunk being uploaded.
     * @return The result of the upload.
     */
    String uploadChunk(MultipartFile file, Integer chunkNumber, Integer totalChunks, String fileName,
                       String originalFileHash, String contentRange);

}
