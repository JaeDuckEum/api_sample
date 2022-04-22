package com.softsquared.template.utils;

import com.softsquared.template.config.BaseException;
import com.softsquared.template.config.BaseResponse;
import com.softsquared.template.config.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public BaseResponse<BaseResponseStatus> handleBaseException(BaseException e) {
        log.error("BaseException has occured. this is business exception", e);
        return new BaseResponse(e.getStatus());
    }
}
