package com.quarks.cooking.aspect;
import com.quarks.cooking.pojo.common.ResultCodeEnum;
import com.quarks.cooking.pojo.common.Msg;
import com.quarks.cooking.pojo.common.DefaultHttpRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.stream.Collectors;

/**
 * 服务器的统一异常处理器，所有服务器在Controller抛出的异常都会在这里处理
 *
 * @author Leonardo iWzl
 * @version 1.0
 */

@RestController
@RestControllerAdvice
public class ExceptionHandlerAspect implements ErrorController {

    Logger logger = LoggerFactory.getLogger(ExceptionHandlerAspect.class);

    private static final String ERROR_PATH = "/error";


    /**
     * 400异常
     * NoHandlerFoundException 需要Servlet API支持
     * 客户端请求的语法错误，服务器无法理解
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Msg<DefaultHttpRsp> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        logger.error("cannot read json data correctly", e);
        return Msg.buildFailedMsg(ResultCodeEnum.BAD_REQUEST,"cannot read json data correctly");
    }


    /**
     * 处理 @RequestBody方法验证失败
     * @param e 异常信息
     * @return 方法提示
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Msg<DefaultHttpRsp> handleValidationException(MethodArgumentNotValidException e) {
        logger.error("method parameter verification failed", e);
        String error = e.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> String.format("[%s:%s]",((FieldError)objectError).getField(), objectError.getDefaultMessage()))
                .collect(Collectors.joining(","));
        return Msg.buildFailedMsg(ResultCodeEnum.BAD_REQUEST,error);
    }

    /**
     * 处理不带任何注解的参数绑定校验异常
     * @param e 异常信息
     * @return 提示返回
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Msg<DefaultHttpRsp> handleBingException(BindException e) {
        String errorMsg = e.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> String.format("[%s:%s]",((FieldError)objectError).getField(), objectError.getDefaultMessage()))
                .collect(Collectors.joining(","));
        return Msg.buildFailedMsg(ResultCodeEnum.BAD_REQUEST,errorMsg);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Msg<DefaultHttpRsp> handleValidationException(ValidationException e) {
        logger.error("parameter verification failed", e);
        return Msg.buildFailedMsg(ResultCodeEnum.BAD_REQUEST,"parameter verification failed");
    }

    /**
     * 405异常
     * 需要Servlet API支持
     * 客户端请求中的方法被禁止
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Msg<DefaultHttpRsp> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        logger.error("request method type is not supported", e);
        return Msg.buildFailedMsg(ResultCodeEnum.UNKNOWN, "request method type is not supported");
    }


    /**
     * 415 异常
     * 需要Servlet API支持
     * 服务器无法处理请求附带的媒体格式
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Msg<DefaultHttpRsp> handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("content type is not supported", e);
        return Msg.buildFailedMsg(ResultCodeEnum.UNKNOWN, "content type is not supported");
    }


    /**
     * 500 异常
     * 最大的异常处理
     * 服务器内部错误，无法完成请求
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Msg<DefaultHttpRsp> handleException(Exception e) {
        logger.error("web server abnormal", e);
        return Msg.buildFailedMsg(ResultCodeEnum.UNKNOWN, e.getMessage());
    }


    @RequestMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Msg<DefaultHttpRsp>welcomeMessage(){
        return Msg.buildSuccessMsg("welcome to Cooking Master Backend Api Service");
    }

    /**
     * 重写404错误
     * @return 页面找不到的JSON返回信息
     */
    @RequestMapping(value = ERROR_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Msg<DefaultHttpRsp> handleError() {
        logger.error("the requested api or page does not exist");
        return Msg.buildFailedMsg(ResultCodeEnum.NOT_FIND,"the requested api does not exist");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
