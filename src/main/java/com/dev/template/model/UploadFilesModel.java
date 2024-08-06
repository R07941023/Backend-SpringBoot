package com.dev.template.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.template.dto.FileUploadRequest;

@Service
public class UploadFilesModel {

    public Map<String, Object> getFileNames(FileUploadRequest fileUploadRequest){
        Map<String, Object> retData = new HashMap<>();
        List<String> empty_arr = new ArrayList<>();
        retData.put("names", empty_arr);
        int retCode = 0;
        
        // check the files is empty or not
        List<String> names_arr = new ArrayList<>();
        List<MultipartFile> files = fileUploadRequest.getFiles();
        if (files.isEmpty()) {
            retCode = 999;
        }

        // get the files name
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                retCode = 999;
            }
            String fileName = file.getOriginalFilename();
            names_arr.add(fileName);
        }
        
        retData.put("retCode", retCode);
        if (retCode == 0){
            retData.put("names", names_arr);
        }
        return retData;
    }
    
}
