package com.formtools.controller;

import com.alibaba.fastjson.JSON;
import com.formtools.enums.ErrorMsg;
import com.formtools.model.BuiltForm;
import com.formtools.model.DraftForm;
import com.formtools.service.BuiltFormService;
import com.formtools.utils.IdBuilder;
import com.formtools.vo.BuildFormReq;
import com.formtools.vo.ResultVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author myl
 * @create 2020-04-04  21:17
 */
@RestController
@RequestMapping("/manage")
@Validated
public class BuiltFormController {

    @Resource
    private BuiltFormService builtFormService;

    /**
     * 新建表单，创建草稿表单
     * @param userId
     * @return
     */
    @PostMapping("/new/form")
    public ResultVo newForm(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                            @NotEmpty(message = "登录异常,请重新登录") String userId,
                            String formType) {
        Long formId = IdBuilder.getFormId();
        DraftForm draftForm = new DraftForm(
                formId,Long.parseLong(userId),"未命名表单", JSON.parseObject("{}"),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                null,null,formType,false);

        return ResultVo.success();
    }

    /**
     * 发布表单
     *
     * @param userId
     * @param buildFormReq
     * @return
     */
    @PostMapping("/release/form")
    public ResultVo buildForm(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                              @NotEmpty(message = "登录异常,请重新登录") String userId,
                              @RequestBody @Valid BuildFormReq buildFormReq) {
        BuiltForm builtForm = new BuiltForm(
                buildFormReq.getFormId(), Long.parseLong(userId),
                buildFormReq.getFormTitle(), buildFormReq.getFormInfo(),
                new Timestamp(System.currentTimeMillis()), buildFormReq.getBeginTime(),
                buildFormReq.getEndTime(), buildFormReq.getMaxCount(), buildFormReq.getFormType());

        if (builtFormService.releaseForm(builtForm)) return ResultVo.success();
        else return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

}
