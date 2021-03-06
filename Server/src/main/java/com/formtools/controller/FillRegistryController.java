package com.formtools.controller;

import com.formtools.enums.ErrorMsg;
import com.formtools.model.BuiltForm;
import com.formtools.model.FillRegistry;
import com.formtools.service.FillRegistryService;
import com.formtools.utils.CookieUtil;
import com.formtools.utils.ValidationUtil;
import com.formtools.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Api(tags = "活动报名，信息登记")
@RestController
public class FillRegistryController {

    @Resource
    private FillRegistryService fillRegistryService;

    @Resource
    private ValidationUtil validationUtil;

    /**
     * 获取表单题目
     *
     * @param id 表单id
     * @return vo
     */
    @ApiOperation("获取表单")
    @GetMapping("/form-question-info")
    public ResultVo getFormQuestionInfo(@RequestParam("form-id") String id) {
        long formId;
        try {
            formId = Long.parseLong(id);
        } catch (Exception e) {
            return ResultVo.fail(ErrorMsg.FORM_NUMBER_ERROR);
        }
        BuiltForm builtForm = fillRegistryService.getFormInfo(formId);
        if (builtForm == null) {
            return ResultVo.fail(ErrorMsg.FORM_NUMBER_ERROR);
        }
        return ResultVo.success(builtForm);
    }

    /**
     * 填表实时保存
     *
     * @param fillRegistry 答案
     * @param request      HttpServletRequest
     * @param response     HttpServletResponse
     * @return 成功（无数据
     */
    @ApiOperation("保存答案")
    @PostMapping("/current-save")
    public ResultVo currentSaveAnswer(@RequestBody FillRegistry fillRegistry, HttpServletRequest request, HttpServletResponse response) {


        String formId = fillRegistry.getFormId().toString();
        //获取缓存key
        String key = CookieUtil.getKeyFromFormIdCookie(request, formId);
        try {
            //取得缓存的key
            key = fillRegistryService.currentSaveAnswer(key, fillRegistry);
        } catch (Exception e) {
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
        }
        //写入cookie
        Cookie cookie = new Cookie(formId, key);
        cookie.setHttpOnly(true);
        //设置过期时间为7天
        cookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(cookie);
        return ResultVo.success();
    }

    /**
     * 获取答案
     *
     * @param uid     用户id（从cookie获取
     * @param request HttpServletRequest
     * @param fId     表id
     * @return 答案
     */
    @ApiOperation("获取活动报名，信息登记类表单已填的信息")
    @GetMapping("/filled")
    public ResultVo getFilledInfo(@CookieValue("userId")
                                  @NotNull(message = "登录异常 请重新登录")
                                  @NotEmpty(message = "登录异常 请重新登录") String uid, HttpServletRequest request,
                                  @RequestParam("formId") String fId) {
        Long userId;
        Long formId;
        try {
            userId = Long.parseLong(uid);
        } catch (Exception e) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        try {
            formId = Long.parseLong(fId);
        } catch (NumberFormatException e) {
            return ResultVo.fail(ErrorMsg.FORM_NUMBER_ERROR);
        }

        //获取缓存key
        String key = CookieUtil.getKeyFromFormIdCookie(request, fId);

        FillRegistry fillRegistry;
        try {
            fillRegistry = fillRegistryService.getAnswer(userId, formId, key);
        } catch (Exception e) {
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
        }
        //转化
        return ResultVo.success(fillRegistry);
    }

    /**
     * 问卷表 获取答案
     *
     * @param fId     表id
     * @param request HttpServletRequest
     * @return 成功+数据（数据可为空
     */
    @ApiOperation("获取问卷类表单已填的信息")
    @GetMapping("/questionnaire/filled")
    public ResultVo getQuestionnaireAnswer(@RequestParam("formId") String fId, HttpServletRequest request) {
        //获取缓存key
        String key = CookieUtil.getKeyFromFormIdCookie(request, fId);

        //若缓存不存在
        if (key == null) return ResultVo.success();
        FillRegistry fillRegistry;
        try {
            //只查缓存
            fillRegistry = fillRegistryService.getAnswer(null, null, key);
        } catch (Exception e) {
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
        }
        return ResultVo.success(fillRegistry);
    }

    /**
     * 提交非问卷类 答案
     *
     * @param uid          用户id（验证登录
     * @param fillRegistry 答案
     * @param request      HttpServletRequest
     * @param response     HttpServletRespond
     * @return 成功||失败
     */
    @ApiOperation("提交活动报名，信息登记类表单")
    @PostMapping("/filled")
    public ResultVo insertFillRegistry(@CookieValue("userId")
                                       @NotNull(message = "登录异常 请重新登录")
                                       @NotEmpty(message = "登录异常 请重新登录") String uid,
                                       @RequestBody FillRegistry fillRegistry, HttpServletRequest request, HttpServletResponse response) {
        //参数校验
        validationUtil.validateParam(fillRegistry, new Class[]{Default.class});
        //若登录的用户id与表单用户id不一致 返回参数错误
        if (!uid.equals(fillRegistry.getUserId().toString())) {
            return ResultVo.fail(ErrorMsg.PARAM_ERROR);
        }

        //获取缓存key
        String key = CookieUtil.getKeyFromFormIdCookie(request, fillRegistry.getFormId().toString());
        //若插入成功
        boolean succeed;
        try {
            succeed = fillRegistryService.insertRegistry(fillRegistry, key);
        } catch (Exception e) {
            //返回重复提交错误
            return ResultVo.fail(ErrorMsg.REPEAT_COMMIT_ERROR);
        }
        if (succeed) {
            //删除cookie
            Cookie cookie = new Cookie(fillRegistry.getFormId().toString(), null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return ResultVo.success();
        }
        //返回提交失败
        return ResultVo.fail(ErrorMsg.COMMIT_FAIL_ERROR);
    }
}
