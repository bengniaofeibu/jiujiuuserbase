package com.jiujiu.handle;

import com.jiujiu.enums.ResultEnums;
import com.jiujiuwisdom.utils.AppletResult;

import com.jiujiuwisdom.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandle {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public AppletResult handle(Exception e) {

        logger.error("[Catch a exception] {}", e);

        if (e instanceof MethodArgumentNotValidException){

            MethodArgumentNotValidException me = (MethodArgumentNotValidException)e;

            return getReturnData(me.getBindingResult());
        }

        if (e instanceof BindException){

            BindException me = (BindException)e;

            return getReturnData(me.getBindingResult());
        }

        return ResultUtil.error(ResultEnums.SERVER_ERROR);
    }

  private AppletResult getReturnData(BindingResult bindingResult){

      FieldError fieldError = bindingResult.getFieldError();

      String data = String.format("参数校验失败 : %s",fieldError.getDefaultMessage());

      return ResultUtil.error(data);
  }
}
