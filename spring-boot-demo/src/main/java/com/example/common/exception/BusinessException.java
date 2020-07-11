package com.example.common.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.example.common.enums.BizExceptionEnum;
import com.example.common.tools.StringUtils;


/**
 * 业务类异常
 * @author Created by xiaolj@jiguang.cn on 2018/11/21.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 4718810982054393564L;

    /** 错误码 */
    private Integer errCode;
    /** 错误提示消息 */
    private String errMsg;

    public BusinessException(BizExceptionEnum bizExceptionEnum){
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = bizExceptionEnum.getErrMsg();
    }

    public BusinessException(BizExceptionEnum bizExceptionEnum, Throwable caused){
        super(caused);
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = bizExceptionEnum.getErrMsg();
    }

    public BusinessException(BizExceptionEnum bizExceptionEnum, String[] formatMsg){
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = StringUtils.formatTemplate(bizExceptionEnum.getErrMsg(), formatMsg);
    }

    public BusinessException(BizExceptionEnum bizExceptionEnum, String formatMsg){
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = String.format(bizExceptionEnum.getErrMsg(), formatMsg);
        // this.errMsg = new MessageFormat(bizExceptionEnum.getErrMsg()).format(formatMsg);
    }

    public BusinessException(Integer errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BusinessException(BizExceptionEnum bizExceptionEnum, String formatMsg, Throwable caused){
        super(caused);
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = StringUtils.formatTemplate(bizExceptionEnum.getErrMsg(), formatMsg);
    }

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static void main(String[] args) {
        BusinessException businessException =
                new BusinessException(BizExceptionEnum.PAYMENT_ORDER_NOT_EXIST);
        System.out.println(businessException);
    }

}
