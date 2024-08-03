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



@RestController
@RequestMapping("/")
public class TemplateController {

    @Autowired
    private CalculationBMI calculationBMI;

    @Operation(summary = "Get")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping({"/template/getPersonSchema"})
    public ResponseEntity<Map<String, Object>> templateGetPersonSchema() {
        // model
        PersonSchema personSchema = new PersonSchema();
        // view
        ResponseStandardSchema responseStandardSchema = new ResponseStandardSchema();
        responseStandardSchema.setRetData(personSchema);
        return responseStandardSchema.output();
    }
    @PostMapping({"/template/caulcuteBMI"})
    public ResponseEntity<Map<String, Object>> templateCaulcuteBMI(@RequestBody PersonSchema personSchema) {
        // model
        Map<String, Object> retData = calculationBMI.process(personSchema);
        // view
        ResponseStandardSchema responseStandardSchema = new ResponseStandardSchema();
        responseStandardSchema.setRetData(retData);
        return responseStandardSchema.output();
    }

}