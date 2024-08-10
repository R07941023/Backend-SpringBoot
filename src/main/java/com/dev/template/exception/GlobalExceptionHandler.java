package com.dev.template.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.dev.constant.CommConstant;

import com.dev.template.dto.RestResult;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResult> handleException(Exception ex) {
        LOGGER.error("Exception occurred: ", ex);

        RestResult restResult = new RestResult();
        restResult.setRetCode(CommConstant.RET_CD_FAIL);
        restResult.setRetDetail(ExceptionUtil.getStackTracetring(ex));
        
        return new ResponseEntity<>(restResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}