package com.formtools.controller;

import com.formtools.enums.ErrorMsg;
import com.formtools.model.UserInfo;
import com.formtools.service.UserService;
import com.formtools.service.impl.FileServiceImpl;
import com.formtools.vo.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLEncoder;

@RestController
public class FileController {

    @Value("${formDataFileDir}")
    private String formDataFileDir;

    @Value("${zipDir}")
    private String zipDir;

    @Resource
    private UserService userService;

    @Resource
    private FileServiceImpl fileService;


    @PostMapping("/file")
    public ResultVo uploadFile(@CookieValue("userId")
                               @NotNull(message = "登录异常 请重新登录")
                               @NotEmpty(message = "登录异常 请重新登录") String uid,
                               @RequestParam("formId") String formId, @RequestParam("file") MultipartFile multipartFile) {
        Long userId;
        try {
            userId = Long.parseLong(uid);
        } catch (NumberFormatException e) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        UserInfo userInfo = userService.getUserInfo(userId);
        String userName = userInfo.getNickname();//获取用户名

        try {
            if (fileService.uploadFile(uid, userName, formId, multipartFile)) {
                //将文件名返回
                return ResultVo.success(multipartFile.getOriginalFilename());
            }
        } catch (IOException e) {
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
        }
        return ResultVo.fail(ErrorMsg.FILE_UPLOAD_ERROR);
    }

    @GetMapping("/file")
    public void downloadFile(@CookieValue("userId")
                             @NotNull(message = "登录异常 请重新登录")
                             @NotEmpty(message = "登录异常 请重新登录") String uid,
                             @RequestParam("formId") String formId,
                             @RequestParam("fileName") String fileName,
                             HttpServletResponse response) {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Long userId = 0L;
        try {
            userId = Long.parseLong(uid);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        UserInfo userInfo = userService.getUserInfo(userId);
        String userName = userInfo.getNickname();//获取用户名

        File file = new File(formDataFileDir + formId + "\\" + uid + userName + "\\" + fileName);
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                inputStream.read(bytes);
                inputStream.close();
                //设置文件MIME类型
                response.setContentType(fileName);
                //设置Content-Disposition
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(bytes);
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes);
                outputStream.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        //返回文件不存在
//        return ResultVo.fail(ErrorMsg.FILE_NOT_EXIT);
    }


    @GetMapping("/zip")
    public void getZip(@CookieValue("userId")
                       @NotNull(message = "登录异常 请重新登录")
                       @NotEmpty(message = "登录异常 请重新登录") String uid,
                       @RequestParam("formId") String formId,
                       HttpServletResponse response) {
        //待打包文件夹位置
        String resourceFile = formDataFileDir + formId;
        //待打包文件夹
        File refile = new File(resourceFile);
        //zip名
        String zipName = formId + ".zip";
        if (refile.exists()) {
            try {
                fileService.createZipFile(resourceFile, zipDir, zipName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        File zipFile = new File(zipDir + zipName);
        if (zipFile.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(zipFile);
                byte[] bytes = new byte[(int) zipFile.length()];
                inputStream.read(bytes);
                inputStream.close();
                //设置文件MIME类型
                response.setContentType(formId);
                //设置Content-Disposition
                response.setHeader("Content-Disposition", "attachment;filename=" + zipFile);
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
