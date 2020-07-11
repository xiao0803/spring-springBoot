package com.example.common.enums;

/**
 * 业务异常类型
 * 范围定义:
 * 501-509 参数验证错误码
 * ...
 *
 * @author Created by xiaolj@jiguang.cn on 2018/11/21.
 */
public enum BizExceptionEnum {

    // 公共错误码
    /**
     * 返回成功
     */
    SUCCESS(0, "success"),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(-1, "未知错误"),
    /**
     * 服务器内部异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器正在打排位赛"),
    URL_NOT_FOUND(404, "访问地址不存在"),
    INVALID_TOKEN(401, "token非法"),
    BAD_REQUEST(400, "非法请求"),
    TOO_MANY_CONNETIONS(429, "您被限流了，先歇一会儿吧"),
    /**
     * 参数验证失败
     */
    PARAM_VALID_FAIL(501, "参数验证失败 [%s]"),

    /**
     * 服务熔断
     */
    HYSTRIX_SERVER(510, "服务熔断"),
    GATEWAY_FALBACK(511, "{0}[{1}]暂不可用，请稍后重试!"),


    //通投白名单相关错误码
    /**
     * 通投白名单-错误编码
     */
    USER_WALLETS_SELECT(520, "XXX服务接口异常"),

    /**
     * 通投白名单-提示编码
     */
    PAYMENT_ORDER_NOT_EXIST(530, "XXX不存在");


    private int errCode;
    private String errMsg;

    BizExceptionEnum(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    public static BizExceptionEnum fromCode(int code) {
        for (BizExceptionEnum c : BizExceptionEnum.values()) {
            if (c.errCode == code) {
                return c;
            }
        }
        return INTERNAL_SERVER_ERROR;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
