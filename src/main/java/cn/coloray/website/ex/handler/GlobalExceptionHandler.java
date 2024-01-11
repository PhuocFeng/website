package cn.coloray.website.ex.handler;

import cn.coloray.website.ex.ServiceException;
import cn.coloray.website.web.JsonResult;
import cn.coloray.website.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public JsonResult serviceExceptionHandler(ServiceException e){
        log.warn("程序运行过程中出现ServiceException，将统一处理！");
        log.warn("异常信息：{}", e.getMessage());
        return JsonResult.fail(e);
    }

    @ExceptionHandler
    public JsonResult bindingExceptionHandler(BindException e){
        log.warn("程序运行过程中出现ServiceException，将统一处理！");
        log.warn("异常信息：{}", e.getMessage());
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST,e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler
    public JsonResult ConstraintViolationExceptionHandler(ConstraintViolationException e){
        log.warn("程序运行过程中出现ConstraintViolationException，将统一处理！");
        log.warn("异常信息：{}", e.getMessage());
        // 由于ConstraintViolationException的API的设计，只能拿到“所有错误”的集合，才能进一步拿到错误的提示文本
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String message = "";
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            message = constraintViolation.getMessage();
        }
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST,message);
    }

    @ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class})
    public JsonResult authenticationExceptionHandler(AuthenticationException e){
        log.warn("程序运行过程中出现AuthenticationException，将统一处理！");
        log.warn("异常信息：{}", e.getMessage());
        String msg = "登录失败，用户名或密码错误！";
        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED,msg);
    }

    @ExceptionHandler
    public JsonResult disabledExceptionHandler(DisabledException e){
        log.warn("程序运行过程中出现DisabledException，将统一处理！");
        log.warn("异常信息：{}", e.getMessage());
        String msg = "登录失败，此账号已经被禁用！";
        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED_DISABLED,msg);
    }

    @ExceptionHandler
    public JsonResult accessDeniedHandler(AccessDeniedException e) {
        log.warn("程序运行过程中出现AccessDeniedException，将统一处理！");
        log.warn("异常信息：{}", e.getMessage());
        String msg = "登录失败，没有访问权限！";
        return JsonResult.fail(ServiceCode.ERR_FORBIDDEN, msg);
    }

    @ExceptionHandler
    public JsonResult handleThrowable(Throwable e) {
        log.warn("程序运行过程中出现Throwable，将统一处理！");
        log.warn("异常类型：{}", e.getClass());
        log.warn("异常信息：{}", e.getMessage());
        String message = "【开发阶段专用】你的服务器端出了异常，请检查服务器端控制台中的异常信息，并补充处理此类异常的方法！";
        // String message = "服务器忙，请稍后再尝试！"; // 项目上线时应该使用此提示文本
        e.printStackTrace(); // 打印异常的跟踪信息，主要是为了在开发阶段更好的检查出现异常的原因
        return JsonResult.fail(ServiceCode.ERR_UNKNOWN, message);
    }
}
