package com.formtools.controller;

import com.alibaba.fastjson.JSONObject;
import com.formtools.enums.ErrorMsg;
import com.formtools.model.BuiltForm;
import com.formtools.model.DraftForm;
import com.formtools.service.BuiltFormService;
import com.formtools.service.DraftFormService;
import com.formtools.utils.IdBuilder;
import com.formtools.vo.ResultVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author myl
 * @create 2020-04-04  21:17
 */
@RestController
@RequestMapping("/manage")
@Validated
public class ManageFormController {

    @Resource
    private BuiltFormService builtFormService;

    @Resource
    private DraftFormService draftFormService;

    /**
     * 通过建表者id查询所有已创建发布的表单的简略信息
     * @param userId
     * @return
     */
    @GetMapping("/all/released/forms")
    public ResultVo allReleasedForms(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                                         @NotEmpty(message = "登录异常,请重新登录") String userId){
        List forms=builtFormService.findAllBuiltForms(Long.parseLong(userId));
        return ResultVo.success(forms);
    }

    /**
     * 查询所有草稿表单的简略信息
     * @param userId
     * @return
     */
    @GetMapping("/all/draft/forms")
    public ResultVo allDraftForms(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                                     @NotEmpty(message = "登录异常,请重新登录") String userId){
        List forms=draftFormService.findAllDraftForms(Long.parseLong(userId));
        return ResultVo.success(forms);
    }

    /**
     * 新建表单，创建草稿表单
     * @param userId
     * @return
     */
    @PostMapping("/new/form")
    public ResultVo newForm(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                            @NotEmpty(message = "登录异常,请重新登录") String userId,
                            @RequestParam String formType) {
        Long formId = IdBuilder.getFormId();
        DraftForm draftForm = new DraftForm(
                formId,Long.parseLong(userId),"未命名表单", new JSONObject(),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()+1000L*3600*24*360*10),100000000,formType,false);
        if(draftFormService.addDraftForm(draftForm))
            return ResultVo.success(draftForm);
        else
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    /**
     * 获取草稿
     * @param userId
     * @param formId
     * @return
     */
    @GetMapping("/draft/form")
    public ResultVo getDraftForm(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                                     @NotEmpty(message = "登录异常,请重新登录") String userId,
                                 @RequestParam String formId){
        DraftForm draftForm=draftFormService.getDraftForm(Long.parseLong(userId),Long.parseLong(formId));
        return ResultVo.success(draftForm);
    }

    /**
     * 复制表单
     * @return
     */
    @GetMapping("/copy/form")
    public ResultVo copyForm(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                                 @NotEmpty(message = "登录异常,请重新登录") String userId,
                             @RequestParam String formId){
        BuiltForm builtForm=builtFormService.copyBuiltForm(Long.parseLong(formId));
        return ResultVo.success(builtForm);
    }

    /**
     * 实时保存表单
     * @return
     */
    @PostMapping("/save/form")
    public ResultVo saveForm(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                                 @NotEmpty(message = "登录异常,请重新登录") String userId,
                             @RequestBody  DraftForm draftForm){
        draftFormService.saveForm(draftForm);
        return ResultVo.success();
    }

    /**
     * 保存草稿
     * @param userId
     * @param draftForm
     * @return
     */
    @PostMapping("save/draft")
    public ResultVo saveDraft(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                                  @NotEmpty(message = "登录异常,请重新登录") String userId,
                              @RequestBody  DraftForm draftForm){
        draftForm.setState(true);
        draftForm.setUserId(Long.parseLong(userId));
        draftFormService.saveDraft(draftForm);
        return ResultVo.success();
    }

    /**
     * 发布表单
     *
     * @param userId
     * @param draftForm
     * @return
     */
    @PostMapping("/release/form")
    public ResultVo buildForm(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                              @NotEmpty(message = "登录异常,请重新登录") String userId,
                              @RequestBody  DraftForm draftForm) {
        BuiltForm builtForm=draftForm;
        builtForm.setUserId(Long.parseLong(userId));
        builtForm.setBuiltTime(new Timestamp(System.currentTimeMillis()));
        if (builtFormService.releaseForm(builtForm))
            return ResultVo.success();
        else
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("/delete/form")
    public ResultVo deleteForm(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                                   @NotEmpty(message = "登录异常,请重新登录") String userId,
                               @RequestParam String formId){
        if(builtFormService.deleteBuiltForm(Long.parseLong(formId),Long.parseLong(userId)))
            return ResultVo.success();
        else
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("/delete/draft")
    public ResultVo deleteDraft(@CookieValue("userId") @NotNull(message = "登录异常,请重新登录")
                               @NotEmpty(message = "登录异常,请重新登录") String userId,
                               @RequestParam String formId){
        if(draftFormService.deleteDraft(Long.parseLong(formId),Long.parseLong(userId)))
            return ResultVo.success();
        else
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }
}
