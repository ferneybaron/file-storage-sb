package com.fbaron.core.repository;

import java.io.IOException;

/**
 * The FindChunkRepository interface represents a repository for finding a chunk by its ID.
 *
 * @author Ferney Estupinan Baron
 * @since 12/24/2023
 */
public interface FindChunkRepository {

    /**
     * Finds a chunk by its ID.
     * @param fileName The name of the file.
     * @param chunkId The id of the chunk.
     */
    byte[] findChunkById(String fileName, Integer chunkId) throws IOException;

}
