package com.evnit.ttpm.AuthApp.model.category.ThoaThuan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileData {
    private String afFileId;
    private String fileName;
    private String base64;
    private Integer size;
    private String filePath;
}
