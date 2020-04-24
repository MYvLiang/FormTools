package com.formtools.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl {

    @Value("${formDataFileDir}")
    private String formDataFileDir;

    @Transactional(rollbackFor = Exception.class)
    public boolean uploadFile(String userId, String userName, String formId, MultipartFile multipartFile) throws IOException {
        File fileDir = new File(formDataFileDir + formId + "\\" + userId + userName);//该用户于该表中文件储存位置
        if (!fileDir.exists()) {
            //创建文件夹
            if (!fileDir.mkdirs()) {
                return false;
            }
        }
        File file=new File(fileDir.getAbsolutePath()+"\\"+multipartFile.getOriginalFilename());//需保存的文件
        if (file.exists()){
            if (!file.delete()){
                return false;
            }
        }
        if (file.createNewFile()){
            multipartFile.transferTo(file);
            return true;
        }
        return false;
    }
}
