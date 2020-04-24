package com.formtools.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        File file = new File(fileDir.getAbsolutePath() + "\\" + multipartFile.getOriginalFilename());//需保存的文件
        if (file.exists()) {
            if (!file.delete()) {
                return false;
            }
        }
        if (file.createNewFile()) {
            multipartFile.transferTo(file);
            return true;
        }
        return false;
    }

    /**
     * @param sourceFilePath 待压缩文件（夹）路径
     * @param targetPath     压缩文件所在目录
     * @param zipFileName    压缩后的文件名称{.zip结尾}
     * @return
     * @Description: 创建zip文件
     */
    public boolean createZipFile(String sourceFilePath, String targetPath, String zipFileName) throws FileNotFoundException {

        boolean flag = false;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        // 要压缩的文件资源
        File sourceFile = new File(sourceFilePath);
        // zip文件存放路径
        String zipPath = "";

        if (null != targetPath && !"".equals(targetPath)) {
            zipPath = targetPath + File.separator + zipFileName;
        } else {
            zipPath = new File(sourceFilePath).getParent() + File.separator + zipFileName;
        }

        if (!sourceFile.exists()) {
            System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
            return flag;
        }

        try {
            File zipFile = new File(zipPath);
            if (zipFile.exists()) {
                zipFile.delete();
            }
            File[] sourceFiles = sourceFile.listFiles();

            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(new BufferedOutputStream(fos));
            // 生成压缩文件
            writeZip(sourceFile, "", zos);
            flag = true;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != zos) {
                    zos.close();
                }
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * @param file
     * @param parentPath
     * @param zos
     * @Description:
     */
    private void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if (file.exists()) {
            // 处理文件夹
            if (file.isDirectory()) {
                parentPath += file.getName() + File.separator;
                File[] files = file.listFiles();
                if (files != null && files.length != 0) {
                    for (File f : files) {
                        // 递归调用
                        writeZip(f, parentPath, zos);
                    }
                } else {
                    // 空目录则创建当前目录的ZipEntry
                    try {
                        zos.putNextEntry(new ZipEntry(parentPath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte[] content = new byte[1024];
                    int len;
                    while ((len = fis.read(content)) != -1) {
                        zos.write(content, 0, len);
                        zos.flush();
                    }
                } catch (IOException e) {
                    System.out.println(("创建ZIP文件失败"));
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        System.out.println(("创建ZIP文件失败"));
                    }
                }
            }
        }
    }
}
