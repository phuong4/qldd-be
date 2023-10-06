package com.evnit.ttpm.AuthApp.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadDataDto {
    private String fileName;
    private String base64;
    private Integer size;
}
