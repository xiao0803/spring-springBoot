package com.example.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.jiguang.msa.rest.common.MsaResp;

/**
 * 统一异常处理
 * 
 * @author xiaolj
 * @date 2015年1月24日
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常处理
     * @param ex
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(BusinessException.class)
    public MsaResp handleBusinessException(BusinessException ex) {
        logger.error(ex.getErrMsg(), ex);
        return MsaResp.buildFail(ex.getErrCode(), ex.getErrMsg());
    }

    /**
     * 系统内部异常处理
     * @param ex
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(Exception.class)
    public MsaResp handleAllException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return MsaResp.buildFail();
    }

}
