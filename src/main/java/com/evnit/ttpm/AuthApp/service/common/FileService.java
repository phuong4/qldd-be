package com.evnit.ttpm.AuthApp.service.common;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.file.AF_OTHER;
import com.evnit.ttpm.AuthApp.model.accreditation.FileData;
import com.evnit.ttpm.AuthApp.model.common.FileUploadDataDto;
import com.evnit.ttpm.AuthApp.model.common.FileUploadResponseDto;
import com.evnit.ttpm.AuthApp.repository.file.AFOtherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    AFOtherRepository afOtherRepository;

    @Value("${upload.dir}")
    private String uploadDir;

    public ResponseData saveFiles(List<MultipartFile> files, AF_OTHER afOther) throws IOException {
        ResponseData response = new ResponseData();
        try {

            String endPointDir = uploadDir + "/" + afOther.getObjTypeId();
            Path directoryPath = Paths.get(endPointDir);
            Files.createDirectories(directoryPath);
            for (MultipartFile file : files) {
                if (!files.isEmpty()) {
                    String uniqueFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    Path targetLocation = directoryPath.resolve(uniqueFileName);
                    AF_OTHER entity = new AF_OTHER();
                    entity.setAfFileId(UUID.randomUUID().toString());
                    entity.setCrdTime(new Date());
                    entity.setUserId(afOther.getUserId());
                    entity.setObjId(afOther.getObjId());
                    entity.setOrgId(afOther.getOrgId());
                    entity.setFileSizeB((int) file.getSize());
                    entity.setObjTypeId(afOther.getObjTypeId());
                    entity.setObjId(afOther.getObjId());
                    entity.setFilePath(endPointDir + "/" + uniqueFileName);
                    entity.setFileName(endPointDir + "/" + uniqueFileName);
                    Files.copy(file.getInputStream(), targetLocation);
                }
            }
            response.setData(afOther.getFilePath());
            response.setState(ResponseData.STATE.OK.toString());
            response.setState(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {

        }
        return response;
    }

    public ResponseEntity<InputStreamResource> getFileById(String FileId) throws IOException {

        if (FileId == null || FileId.isEmpty()) {
            throw new IOException("FileId could not be null!");
        }
        var entity = afOtherRepository.findById(FileId).orElse(null);
        if (entity != null) {
            String FileName = entity.getFileName();
            String[] SplitedFileName = FileName.split("\\.");
            String FileExtension = "";
            if (SplitedFileName != null && SplitedFileName.length > 0)
                FileExtension = SplitedFileName[SplitedFileName.length - 1].toLowerCase();
            MediaType DynamicContentType = MediaType.APPLICATION_OCTET_STREAM;
            boolean isDirectShow = false;
            switch (FileExtension) {
                case "":
                    break;
                case "gif":
                    DynamicContentType = MediaType.IMAGE_GIF;
                case "jpg":
                    isDirectShow = true;
                    DynamicContentType = MediaType.IMAGE_JPEG;
                    break;
                case "jpeg":
                    isDirectShow = true;
                    DynamicContentType = MediaType.IMAGE_JPEG;
                    break;
                case "png":
                    isDirectShow = true;
                    DynamicContentType = MediaType.IMAGE_PNG;
                    break;
                case "txt":
                    DynamicContentType = MediaType.TEXT_HTML;
                    break;
                case "xml":
                    DynamicContentType = MediaType.APPLICATION_XHTML_XML;
                    break;
                case "json":
                    DynamicContentType = MediaType.APPLICATION_STREAM_JSON;
                    break;
                case "xls":
                    break;
                case "xlsx":
                    break;
                case "doc":
                    break;
                case "docx":
                    break;
                case "pdf":
                    isDirectShow = true;
                    DynamicContentType = MediaType.APPLICATION_PDF;
                    break;
                case "mp4":
                    break;
                case "avi":
                    break;
                case "wmv":
                    break;
                default:
                    DynamicContentType = MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE);

            }
            File initialFile = new File(entity.getFilePath());
            InputStream in = new FileInputStream(initialFile);
            if (isDirectShow) {
                return ResponseEntity.ok().header("", "attachment; filename=" + FileName).contentType(DynamicContentType).body(new InputStreamResource(in));
            } else {
                return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + FileName).contentType(DynamicContentType).body(new InputStreamResource(in));

            }
        } else throw new IOException("File not found!");
    }

    public FileUploadResponseDto UpLoadFileBase64(FileUploadDataDto filedata, String userId) throws Exception {
        try {

            var response = new FileUploadResponseDto();
            var filePath = Base64ToFile(filedata, "CommonFileUpload");
            //save file to db
            AF_OTHER entity = new AF_OTHER();
            entity.setAfFileId(UUID.randomUUID().toString());
            entity.setCrdTime(new Date());
            entity.setUserId(userId);
            entity.setObjId(null);
            entity.setOrgId("0");
            entity.setObjTypeId(AF_OTHER.AF_TYPE.KD.toString());
            entity.setFileSizeB((int) filedata.getSize());
            entity.setFilePath(filePath);
            entity.setFileName(filedata.getFileName());
            var addedEntitie = afOtherRepository.saveAndFlush(entity);
            response.setFilePath(filePath);
            response.setFileName(filedata.getFileName());
            response.setSize(filedata.getSize());
            response.setAfFileId(addedEntitie.getAfFileId());

            return response;

        } catch (Exception ex) {
            throw ex;
        }
    }

    private String Base64ToFile(FileUploadDataDto filedata, String subDirectory) throws Exception {
        try {
            Path currentDirectory = Paths.get("");
            Path fileDirectory = Paths.get(currentDirectory.toString(), uploadDir + "/" + subDirectory);
            if (!Files.exists(fileDirectory)) {
                File f = new File(fileDirectory.toUri());
                f.mkdirs();
            }
            String file_name = UUID.randomUUID().toString();
            String file_Extension = "";
            if (filedata.getFileName().contains(".")) {
                var name = filedata.getFileName();
                var filenames = name.split("\\.");
                file_Extension = filenames[filenames.length - 1];
            }
            String file_DataStr = filedata.getBase64().split(",")[1];
            byte[] file_DataByte = Base64.getDecoder().decode(file_DataStr);
            OutputStream out = new FileOutputStream(new File(fileDirectory + "\\" + file_name + "." + file_Extension));
            out.write(file_DataByte);
            out.close();
            return fileDirectory + "\\" + file_name + "." + file_Extension;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    private void DeleteFile(FileUploadResponseDto filedata) throws Exception {
        try {
            String path = filedata.getFilePath();
            if (path.length() <= 0) throw new Exception("File path can not null");
            if (filedata.getAfFileId() == null || filedata.getAfFileId() == "")
                throw new Exception("afFileId can not null");
            Path currentDirectory = Paths.get("");
            Path fileDirectory = Paths.get(currentDirectory.toString(), uploadDir, path);
            if (Files.exists(fileDirectory)) {
                File file = new File(fileDirectory.toUri());
                file.delete();
            }

        } catch (Exception ex) {
            throw new Exception("Error when delete a file");
        }
    }
}
