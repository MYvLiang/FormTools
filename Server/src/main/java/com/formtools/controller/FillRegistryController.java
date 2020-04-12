package com.formtools.controller;

import com.formtools.enums.ErrorMsg;
import com.formtools.model.BuiltForm;
import com.formtools.service.FillRegistryService;
import com.formtools.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FillRegistryController {

    @Resource
    private FillRegistryService fillRegistryService;

    @GetMapping("/form-question-info")
    public ResultVo getFormQuestionInfo(@RequestParam("form-id") String id){
        long formId;
        try {
            formId = Long.parseLong(id);
        }catch (Exception e){
            return ResultVo.fail(ErrorMsg.FORM_NUMBER_ERROR);
        }
        BuiltForm builtForm=fillRegistryService.getFormInfo(formId);
        if (builtForm == null){
            return ResultVo.fail(ErrorMsg.FORM_NUMBER_ERROR);
        }
        return ResultVo.success(builtForm);
    }
}
