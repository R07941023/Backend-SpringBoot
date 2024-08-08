package com.dev.template.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.template.dto.RestResult;
import com.dev.template.exception.Timeout;
import com.dev.constant.CommConstant;


import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/")
public class TemplateErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateController.class);

    @Operation(summary = "Get", description = "template")
    @GetMapping({"/template/getTimeoutError"})
    @Timeout(CommConstant.DEFAULT_TIMEOUT)
    public RestResult templateGetTimeoutError() throws InterruptedException{
        LOGGER.info("========== TemplateController templateGetTimeoutError START ==========");
        Thread.sleep(100);
        RestResult restResult = new RestResult();
        LOGGER.info("========== TemplateErrorHandler templateGetTimeoutError END ==========");
        return restResult;
    }

    @Operation(summary = "Get", description = "template")
    @GetMapping({"/template/getBugError"})
    public RestResult templateGetBugError() throws InterruptedException{
        LOGGER.info("========== TemplateController templateGetBugError START ==========");
        // View
        RestResult restResult = new RestResult();
        int errorVal = 5/0;
        LOGGER.info("========== TemplateController templateGetBugError END ==========");
        return restResult;
    }
    
}
