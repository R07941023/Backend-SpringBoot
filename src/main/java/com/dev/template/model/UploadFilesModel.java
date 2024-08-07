package com.dev.template.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.template.dto.FileUploadRequest;

@Service
public class UploadFilesModel {

    public List<String> getFileNames(FileUploadRequest fileUploadRequest){
        List<String> names = new ArrayList<>();
        
        
        List<MultipartFile> files = fileUploadRequest.getFiles();
        // check the files is empty or not
        if (files.isEmpty()) {
            return names;
        }

        // get the files name
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            String fileName = file.getOriginalFilename();
            names.add(fileName);
        }
        return names;
    }
    
}
