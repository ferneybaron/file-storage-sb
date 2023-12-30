package com.fbaron.web.rest;

import com.fbaron.core.usecase.UploadChunkUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ferney Estupinan Baron
 * @since 12/22/2023
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/file-storage")
@RequiredArgsConstructor
public class FileStorageRestAdapter {

    private final UploadChunkUseCase uploadChunkUseCase;

    @PostMapping("/chunk-upload")
    public ResponseEntity<String> uploadChunk(@RequestParam("file") MultipartFile file,
                                              @RequestParam("chunkNumber") Integer chunkNumber,
                                              @RequestParam("totalChunks") Integer totalChunks,
                                              @RequestParam("fileName") String fileName,
                                              @RequestParam("originalFileHash") String originalFileHash,
                                              @RequestHeader("Content-Range") String contentRange) {

        var uploadedChunk = uploadChunkUseCase
                .uploadChunk(file, chunkNumber, totalChunks, fileName, originalFileHash, contentRange);
        return ResponseEntity.ok(uploadedChunk);
    }

}
