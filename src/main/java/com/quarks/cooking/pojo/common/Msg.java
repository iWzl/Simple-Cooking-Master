package com.quarks.cooking.pojo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 消息的统一回复类,提供消息回复统一模板
 * 项目使用JSON,通过这种方式，构造通用的JSON返回包装类
 *
 * @author Leonardo iWzl
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Msg<T extends HttpResponse> implements Serializable {
    private static final String SUCCESS = "SUCCESS";

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Meta meta;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * 隐藏构造器,规范化项目操作，通过内部的静态方法统一创建管理
     * @author Leo Wang
     */
    private Msg(){
    }

    public static Msg<DefaultHttpRsp> buildSuccessMsg(){
        Msg<DefaultHttpRsp> msg = new Msg<>();
        msg.meta = new Meta(ResultCodeEnum.SUCCESS_NO_CONTENT.getCode(),SUCCESS);
        return msg;
    }

    public static Msg<DefaultHttpRsp> buildSuccessMsg(String msg){
        Msg<DefaultHttpRsp> serviceResponseMessage = new Msg<>();
        serviceResponseMessage.meta = new Meta(ResultCodeEnum.SUCCESS_NO_CONTENT.getCode(),msg);
        return serviceResponseMessage;
    }

    public static <T extends HttpResponse> Msg<T> buildSuccessMsg(ResultCodeEnum resultCode, T data){
        Msg<T> msg = new Msg<>();
        msg.meta = new Meta(resultCode.getCode(),SUCCESS);
        msg.data = data;
        return msg;
    }

    public static  <T extends HttpResponse> Msg<T> buildSuccessMsg(T data){
        Msg<T> msg = new Msg<>();
        msg.meta = new Meta(ResultCodeEnum.SUCCESS.getCode(),SUCCESS);
        msg.data = data;
        return msg;
    }


    public static  <T extends HttpResponse> Msg<T> buildSuccessMsg(String msg, T data){
        Msg<T> serviceResponseMessage = new Msg<>();
        serviceResponseMessage.meta = new Meta(ResultCodeEnum.SUCCESS.getCode(),msg);
        serviceResponseMessage.data = data;
        return serviceResponseMessage;
    }


    public static <T extends HttpResponse> Msg<T> buildFailedMsg(String msg){
        Msg<T> serviceResponseMessage = new Msg<>();
        serviceResponseMessage.meta = new Meta(ResultCodeEnum.BAD_REQUEST.getCode(),msg);
        return serviceResponseMessage;
    }


    public static <T extends HttpResponse> Msg<T> buildFailedMsg(ResultCodeEnum resultCode, String msg){
        Msg<T> serviceResponseMessage = new Msg<>();
        serviceResponseMessage.meta = new Meta(resultCode.getCode(),msg);
        return serviceResponseMessage;
    }

    public static <T extends HttpResponse> Msg<T> buildUnauthorizedFailedMsg(ResultCodeEnum resultCode, String msg){
        Msg<T> serviceResponseMessage = new Msg<>();
        serviceResponseMessage.meta = new Meta(resultCode.getCode(),msg);
        return serviceResponseMessage;
    }


    public static <T extends HttpResponse> Msg<T> buildMsg(ResultCodeEnum resultCode, String msg, T data){
        Msg<T> serviceResponseMessage = new Msg<>();
        serviceResponseMessage.meta = new Meta(resultCode.getCode(),msg);
        serviceResponseMessage.data = data;
        return serviceResponseMessage;
    }


    /**
     * 消息返回的元数据
     * 包含消息的返回码和提示信息
     *
     * @author Leonardo iWzl
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class Meta{
        @JsonProperty
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private int code;
        @JsonProperty
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String msg;

        private Meta(int resultCode, String message) {
            this.code = resultCode;
            this.msg = message;
        }
    }
}
