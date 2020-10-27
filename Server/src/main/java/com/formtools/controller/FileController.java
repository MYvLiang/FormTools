package com.formtools.controller;

import com.formtools.enums.ErrorMsg;
import com.formtools.model.UserInfo;
import com.formtools.service.UserService;
import com.formtools.service.impl.FileServiceImpl;
import com.formtools.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags = "文件api")
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


    /**
     * 上传文件，文件无限制
     * 储存路径为  "（上线需更改配置文件）formDataFileDir/表单id/用户id+用户名”
     * 成功返回文件名
     * 失败返回错误信息
     * @param uid
     * @param formId @RequestParam("formId")表单id
     * @param multipartFile @RequestParam("file")文件
     * @return
     */
    @ApiOperation("上传文件")
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

    /**
     * 下载文件
     * 请求该接口则直接下载文件
     * @param uid
     * @param formId @RequestParam("formId")表单id
     * @param fileName @RequestParam("fileName")文件名
     * @param response
     */
    @ApiOperation("下载文件")
    @GetMapping("/file")
    public void downloadFile(@CookieValue("userId")
                             @NotNull(message = "登录异常 请重新登录")
                             @NotEmpty(message = "登录异常 请重新登录") String uid,
                             @RequestParam("formId") String formId,
                             @RequestParam("fileName") String fileName,
                             HttpServletResponse response) {
        Long userId = 0L;
        try {
            userId = Long.parseLong(uid);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        UserInfo userInfo = userService.getUserInfo(userId);
        String userName = userInfo.getNickname();//获取用户名
        File file = new File(formDataFileDir + formId + "\\" + uid + userName + "\\" + fileName);
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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


    /**
     * 打包下载
     * 将“（上线需更改配置文件）formDataFileDir/表单id”路径下的文件打包并将zip包放置于zipDir（上线需更改配置文件）下
     * 然后下载
     * 请求即下载
     * @param uid
     * @param formId @RequestParam("formId")表单id
     * @param response
     */
    @ApiOperation("打包表单收集的附件下载")
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

    /**
     * 上传文件若为图片时需用该接口展示。
     * @param uid
     * @param formId @RequestParam("formId") String formId表单id
     * @param imageName @RequestParam("image") String imageName图片名，即上传文件接口处所返回的文件名
     * @param response
     * @throws IOException
     */
    @ApiOperation("获取图片")
    @GetMapping("/image")
    public void getImage(@CookieValue("userId")
                           @NotNull(message = "登录异常 请重新登录")
                           @NotEmpty(message = "登录异常 请重新登录") String uid,
                           @RequestParam("formId") String formId,
                           @RequestParam("image") String imageName,
                           HttpServletResponse response) throws IOException {
        String reg = ".+(.JPG|.jpg|.GIF|.gif|.PNG|.png)$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(imageName);

        Long userId;
        try {
            userId=Long.parseLong(uid);
        } catch (NumberFormatException e) {
            return;
        }
        if (matcher.find()){
            String userDir=uid+userService.getUserInfo(userId).getNickname();
            File image=new File(formDataFileDir+formId+"\\"+userDir+"\\"+imageName);
            if (image.exists()){
                FileInputStream fileInputStream=new FileInputStream(image);
                byte[] bytes=new byte[fileInputStream.available()];
                if (fileInputStream.read(bytes)>0){
                    OutputStream outputStream=response.getOutputStream();
                    outputStream.write(bytes);
                    outputStream.close();
                }
                fileInputStream.close();
            }
        }
    }
}
