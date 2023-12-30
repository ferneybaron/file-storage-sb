package com.fbaron.core.repository;

import java.io.IOException;

/**
 * The DeleteChunkRepository interface represents a repository for deleting a chunk by its ID.
 *
 * @author Ferney Estupinan Baron
 * @since 12/24/2023
 */
public interface DeleteChunkRepository {

    /**
     * Delete a chunk by its ID.
     * @param fileName The name of the file.
     * @param chunkId The id of the chunk.
     */
    void deleteChunkById(String fileName, Integer chunkId) throws IOException;

}
