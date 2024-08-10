package com.dev.template.dto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequest {

    private List<MultipartFile> files;
    private String description;

    // Getters and setters

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}