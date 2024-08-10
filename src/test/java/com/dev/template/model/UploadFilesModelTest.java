package com.dev.template.model;

import com.dev.template.dto.FileUploadRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UploadFilesModelTest {

    @Autowired
    private UploadFilesModel uploadFilesModel;

    @Test
    public void testGetFileNamesWithFiles() {
        // question
        MultipartFile file1 = new MockMultipartFile("file1", "file1.txt", "text/plain", "Test Content 1".getBytes());
        MultipartFile file2 = new MockMultipartFile("file2", "file2.txt", "text/plain", "Test Content 2".getBytes());

        // testing
        FileUploadRequest request = new FileUploadRequest();
        request.setFiles(Arrays.asList(file1, file2));
        List<String> fileNames = uploadFilesModel.getFileNames(request);
        assertEquals(2, fileNames.size());
        assertTrue(fileNames.contains("file1.txt"));
        assertTrue(fileNames.contains("file2.txt"));
    }

    @Test
    public void testGetFileNamesWithEmptyRequest() {
        // question
        FileUploadRequest request = new FileUploadRequest();
        request.setFiles(Arrays.asList());

        // testing
        List<String> fileNames = uploadFilesModel.getFileNames(request);
        assertTrue(fileNames.isEmpty());
    }

    @Test
    public void testGetFileNamesWithEmptyFiles() {
        // question
        MultipartFile emptyFile = new MockMultipartFile("emptyFile", "", "text/plain", new byte[0]);
        FileUploadRequest request = new FileUploadRequest();
        request.setFiles(Arrays.asList(emptyFile));

        // testing
        List<String> fileNames = uploadFilesModel.getFileNames(request);
        assertTrue(fileNames.isEmpty());
    }
}