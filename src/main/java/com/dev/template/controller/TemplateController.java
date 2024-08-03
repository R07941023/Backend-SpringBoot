package com.dev.template.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.dev.template.model.*;
import com.dev.template.schema.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@RestController
@RequestMapping("/")
public class TemplateController {

    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    private CalculationBMI calculationBMI;

    @Operation(summary = "Get")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping({"/template/getPersonSchema"})
    public ResponseEntity<Map<String, Object>> templateGetPersonSchema(
        @RequestHeader("UUID") String uuid,
        @RequestHeader("Authorization") String token) {
        logger.info("UUID:" + uuid);
        // model
        PersonSchema personSchema = new PersonSchema();
        // view
        ResponseStandardSchema responseStandardSchema = new ResponseStandardSchema();
        responseStandardSchema.setRetData(personSchema);
        return responseStandardSchema.output();
    }
    @PostMapping({"/template/caulcuteBMI"})
    public ResponseEntity<Map<String, Object>> templateCaulcuteBMI(
        @RequestHeader("UUID") String uuid,
        @RequestHeader("Authorization") String token,
        @RequestBody PersonSchema personSchema) {
        logger.info("UUID:" + uuid);
        // model
        Map<String, Object> retData = calculationBMI.process(personSchema);
        // view
        ResponseStandardSchema responseStandardSchema = new ResponseStandardSchema();
        responseStandardSchema.setRetData(retData);
        return responseStandardSchema.output();
    }

}