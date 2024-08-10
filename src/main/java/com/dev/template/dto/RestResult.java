package com.dev.template.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

public class RestResult {

    @Schema(description = "result code", example = "0")
    private int retCode;

    @Schema(description = "result details", example = "OK")
    private String retDetail;

    @Schema(description = "result data", example = "{}")
    private transient Object retObject;

    // set
    public void setRetCode(int retCode){
        this.retCode = retCode;
    }
    public void setRetDetail(String retDetail){
        this.retDetail = retDetail;
    }
    public void setRetObject(Object retObject){
        this.retObject = retObject;
    }

    // element
    public int getRetCode() {
        return retCode;
    }
    
    public String getRetDetail() {
        return retDetail;
    }

    public Object getRetObject() {
        return retObject;
    }
}
