package com.fbaron.data.local;

import com.fbaron.core.model.FileUpload;
import com.fbaron.core.repository.DeleteChunkRepository;
import com.fbaron.core.repository.FindChunkRepository;
import com.fbaron.core.repository.SaveChunkRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Ferney Estupinan Baron
 * @since 12/24/2023
 */
public class LocalStorageAdapter implements SaveChunkRepository, FindChunkRepository, DeleteChunkRepository {

    /**
     * Saves a chunk of data at repository level.
     *
     * @param fileUpload The file to save the chunk to.
     */
    @Override
    public void saveChunk(FileUpload fileUpload) throws IOException {
        Path tempDirPath = Path.of("temp");
        Files.createDirectories(tempDirPath);

        Path chunkFilePath = tempDirPath
                .resolve(fileUpload.getFileName() + "_chunk" + fileUpload.getChunkNumber());
        //Files.write(chunkFilePath, fileUpload.getFile());
    }

    /**
     * Finds a chunk by its ID.
     *
     * @param fileName The name of the file.
     * @param chunkId  The id of the chunk.
     */
    @Override
    public byte[] findChunkById(String fileName, Integer chunkId) throws IOException {
        Path tempDirPath = Path.of("temp");
        Path partialFilePath = tempDirPath.resolve(fileName + "_chunk" + chunkId);
        return Files.readAllBytes(partialFilePath);
    }

    /**
     * Delete a chunk by its ID.
     *
     * @param fileName The name of the file.
     * @param chunkId  The id of the chunk.
     */
    @Override
    public void deleteChunkById(String fileName, Integer chunkId) throws IOException {
        Path tempDirPath = Path.of("temp");
        Path partialFilePath = tempDirPath.resolve(fileName + "_chunk" + chunkId);
        Files.deleteIfExists(partialFilePath);
    }

}
