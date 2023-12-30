package com.fbaron.core.usecase;


import java.io.InputStream;

/**
 * The CombineChunksUseCase interface defines the use case for combining chunks of a file.
 *
 * @author Ferney Estupinan Baron
 * @since 12/24/2023
 */
public interface CombineChunksUseCase {

    /**
     * Combines chunks of a file into a single file.
     * @param totalChunks The total number of chunks.
     * @param fileName The name of the file.
     * @param originalFileHash The hash of the original file.
     * @return The combined file as an InputStream.
     */
    InputStream combineChunks(Integer totalChunks, String fileName, String originalFileHash);

}
