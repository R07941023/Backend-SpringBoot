package com.dev.template.controller;
import java.util.List;
import java.util.ArrayList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.template.dto.FileUploadRequest;
import com.dev.template.dto.PersonRequest;
import com.dev.template.dto.RestResult;
import com.dev.template.model.CalculationBMI;
import com.dev.template.model.UploadFilesModel;
import com.dev.constant.CommConstant;

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
public class TemplateFunctionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateFunctionController.class);

    @Autowired
    private CalculationBMI calculationBMI;

    @Autowired
    private UploadFilesModel uploadFilesModel;


    @Operation(summary = "Get", description = "template")
    @GetMapping({"/template/getPersonRequest"})
    public RestResult templateGetPersonRequest(
        @RequestHeader("Authorization") String token
        ){
        LOGGER.info("========== TemplateController templateGetPersonRequest START ==========");
        // View
        RestResult restResult = new RestResult();
        List<PersonRequest> personRequests = new ArrayList<>();
        personRequests.add(new PersonRequest());
        restResult.setRetCode(CommConstant.RET_CD_SUCCESS);
        restResult.setRetDetail(CommConstant.SUCCESS);
        restResult.setRetObject(personRequests);
        LOGGER.info("========== TemplateController templateGetPersonRequest END ==========");
        return restResult;
    }
    
    @Operation(summary = "Post", description = "template")
    @PostMapping({"/template/caulcuteBMI"})
    public RestResult templateCaulcuteBMI(
        @RequestHeader("Authorization") String token,
        @RequestBody PersonRequest personRequest) {
        LOGGER.info("========== TemplateController templateCaulcuteBMI START ==========");
        // Model
        calculationBMI.exec(personRequest);
        List<CalculationBMI> retObject = new ArrayList<>();
        retObject.add(calculationBMI);
        // View
        RestResult restResult = new RestResult();
        restResult.setRetCode(CommConstant.RET_CD_SUCCESS);
        restResult.setRetDetail(CommConstant.SUCCESS);
        restResult.setRetObject(retObject);
        LOGGER.info("========== TemplateController templateCaulcuteBMI END ==========");
        return restResult;
    }

    @Operation(summary = "Put", description = "template")
    @PutMapping({"/template/putData"})
    public RestResult templatePutData(
        @RequestHeader("Authorization") String token,
        @RequestBody PersonRequest personRequest) {
        LOGGER.info("========== TemplateController templatePutData START ==========");
        // View
        RestResult restResult = new RestResult();
        restResult.setRetCode(CommConstant.RET_CD_SUCCESS);
        restResult.setRetDetail(CommConstant.SUCCESS);
        LOGGER.info("========== TemplateController templatePutData END ==========");
        return restResult;
    }

    @Operation(summary = "Delete", description = "template")
    @DeleteMapping({"/template/deleteData"})
    public RestResult templateDeleteData(
        @RequestHeader("Authorization") String token,
        @RequestBody PersonRequest personRequest) {
        LOGGER.info("========== TemplateController templateDeleteData START ==========");
        // View
        RestResult restResult = new RestResult();
        restResult.setRetCode(CommConstant.RET_CD_SUCCESS);
        restResult.setRetDetail(CommConstant.SUCCESS);
        LOGGER.info("========== TemplateController templateDeleteData END ==========");
        return restResult;
    }

    @Operation(summary = "Post uploads", description = "template")
    @PostMapping(value="/template/uploads", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public RestResult templateUploads(
        @RequestPart("files") List<MultipartFile> files,
        @RequestPart("description") String description){
        LOGGER.info("========== TemplateController templateUploads START ==========");
        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        fileUploadRequest.setFiles(files);
        fileUploadRequest.setDescription(description);
        // Model
        List<String> retObject = uploadFilesModel.getFileNames(fileUploadRequest);
        // View
        RestResult restResult = new RestResult();
        restResult.setRetCode(CommConstant.RET_CD_SUCCESS);
        restResult.setRetDetail(CommConstant.SUCCESS);
        restResult.setRetObject(retObject);
        LOGGER.info("========== TemplateController templateUploads END ==========");
        return restResult;
    }
}