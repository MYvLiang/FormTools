package com.formtools.Handler;

import com.formtools.Exception.ParamException;
import com.formtools.enums.ErrorMsg;
import com.formtools.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVo ConstraintViolationExceptionHandler(ConstraintViolationException e) {

        Set<ConstraintViolation<?>> set = e.getConstraintViolations();
        Map<String, String> map = new HashMap<>();
        if (set.size() > 0) {
            for (ConstraintViolation<?> cv : set) {
                String[] param = cv.getPropertyPath().toString().split("\\.");
                String message = cv.getMessage();
                map.put(param[param.length - 1], message);
            }
        }

        return ResultVo.fail(ErrorMsg.PARAM_ERROR, map);
    }

    @ExceptionHandler(ParamException.class)
    public ResultVo ParamExceptionHandler(ParamException e){
        return ResultVo.fail(ErrorMsg.PARAM_ERROR,e.getMap());
    }

}
