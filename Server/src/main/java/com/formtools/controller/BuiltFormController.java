package com.formtools.controller;

import com.formtools.vo.BuildFormReq;
import com.formtools.vo.ResultVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author myl
 * @create 2020-04-04  21:17
 */
@RestController
@RequestMapping("/manage")
@Validated
public class BuiltFormController {

    @PostMapping("/form")
    public ResultVo buildForm(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                                  @NotEmpty(message = "登录异常,请重新登录") String userId,
                              @RequestBody @Valid BuildFormReq buildFormReq){

        return ResultVo.success(buildFormReq);
    }

}
