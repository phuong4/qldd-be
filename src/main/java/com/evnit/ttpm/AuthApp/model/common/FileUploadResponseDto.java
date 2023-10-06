package com.evnit.ttpm.AuthApp.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponseDto {
    private String afFileId;
    private String fileName;
    private Integer size;
    private String filePath;
}
