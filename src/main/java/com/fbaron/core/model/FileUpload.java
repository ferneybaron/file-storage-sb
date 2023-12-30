package com.fbaron.core.model;

import lombok.*;

/**
 * @author Ferney Estupinan Baron
 * @since 12/29/2023
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUpload {

    private String fileName;
    private byte[] file;
    private Integer chunkNumber;
    private Integer totalChunks;
    private String originalFileHash;
    private String contentRange;

}
