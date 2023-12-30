package com.fbaron.core.repository;

import com.fbaron.core.model.FileUpload;

import java.io.IOException;

/**
 * The SaveChunkRepository interface represents the use case for saving a chunk of data at repository level.
 * The interface allows for different file systems to be used, such as local storage, cloud storage, or any other file system.
 *
 * @author Ferney Estupinan Baron
 * @since 12/24/2023
 */
public interface SaveChunkRepository {

    /**
     * Saves a chunk of data at repository level.
     *
     * @param fileUpload       The file to save the chunk to.
     */
    void saveChunk(FileUpload fileUpload) throws IOException;

}
