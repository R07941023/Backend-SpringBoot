package com.dev.template.controller;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.template.dto.FileUploadRequest;
import com.dev.template.dto.PersonRequest;
import com.dev.template.dto.ResponseStandardRequest;
import com.dev.template.model.CalculationBMI;
import com.dev.template.model.UploadFilesModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successful operation"),
    @ApiResponse(responseCode = "400", description = "Bad Request"),
    @ApiResponse(responseCode = "401", description = "Unauthorized"),
    @ApiResponse(responseCode = "403", description = "Forbidden"),
    @ApiResponse(responseCode = "404", description = "Not Found"),
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
public class TemplateController {

    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    private CalculationBMI calculationBMI;

    @Autowired
    private UploadFilesModel uploadFilesModel;
    
    @Operation(summary = "Get", description = "template")
    @GetMapping({"/template/getPersonSchema"})
    public ResponseEntity<Map<String, Object>> templateGetPersonSchema(
        @RequestHeader("Authorization") String token) {
        logger.info("UUID:" + "Test");
        // model
        PersonRequest personSchema = new PersonRequest();
        // view
        ResponseStandardRequest responseStandardRequest = new ResponseStandardRequest();
        responseStandardRequest.setRetData(personSchema);
        return responseStandardRequest.output();
    }

    @Operation(summary = "Post", description = "template")
    @PostMapping({"/template/caulcuteBMI"})
    public ResponseEntity<Map<String, Object>> templateCaulcuteBMI(
        @RequestHeader("Authorization") String token,
        @RequestBody PersonRequest personRequest) {
        logger.info("UUID:" + "Test");
        // model
        Map<String, Object> retData = calculationBMI.process(personRequest);
        // view
        ResponseStandardRequest responseStandardRequest = new ResponseStandardRequest();
        responseStandardRequest.setRetData(retData);
        return responseStandardRequest.output();
    }

    @Operation(summary = "Post uploads", description = "template")
    @PostMapping(value="/template/uploads", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> templateUploads(
        @RequestPart("files") List<MultipartFile> files,
        @RequestPart("description") String description){
        logger.info("UUID:" + "Test");
        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        fileUploadRequest.setFiles(files);
        fileUploadRequest.setDescription(description);
        // model
        Map<String, Object> retData = uploadFilesModel.getFileNames(fileUploadRequest);
        // view
        ResponseStandardRequest responseStandardRequest = new ResponseStandardRequest();
        responseStandardRequest.setRetData(retData);
        return responseStandardRequest.output();
    }
}