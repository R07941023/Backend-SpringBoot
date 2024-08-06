package com.dev.template.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

public class ResponseStandardRequest {
    
    @Schema(description = "result code", example = "0")
    private int retCode;

    @Schema(description = "result details", example = "OK")
    private String retDetail;

    @Schema(description = "result data", example = "{}")
    private Object retData = new HashMap<>();

    public ResponseStandardRequest() {
    }

    // output
    public ResponseEntity<Map<String, Object>> output(){
        ResponseEntity res = new ResponseEntity<>(this, HttpStatus.CREATED);
        return res;
    }

    // set
    public void setRetData(Object data){
        this.retData = data;
    }

    // element
    public int getRetCode() {
        return retCode;
    }
    
    public String getRetDetail() {
        return retDetail;
    }

    public Object getRetData() {
        return retData;
    }
}
