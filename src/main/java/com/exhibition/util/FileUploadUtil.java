package com.exhibition.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class FileUploadUtil {
    // 请修改这里的路径
    private final String staticFilepath = "/home/nginx/oss-file/";

    /**
     * 上传文件
     * 
     * @param multipartFile
     * @return 文件存储路径
     */
    public String upload(MultipartFile multipartFile, String staticpath, int uid) {
        // 获取原始文件名
        // String originalFilename = multipartFile.getOriginalFilename();
        // // 获取原始文件名的后缀
        // String suffix =
        // originalFilename.substring(originalFilename.lastIndexOf("."));
        // 拼接新的文件名
        String newFileName = uid + ".jpg";

        System.out.println("lxcr1+++++++++++++ " + staticFilepath);

        String filePath = staticFilepath + staticpath + newFileName;
        System.out.println("lxcr2+++++++++++++ " + filePath);
        File file = new File(filePath);
        // 如果文件已存在，则先删除原文件
        if (file.exists()) {
            file.delete();
        }
        // 文件存储
        try {
            System.out.println("walx+" + file.getAbsolutePath());
            multipartFile.transferTo(file);
        } catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public byte[] load(int uid) {
        String fileName = uid + ".jpg";
        String filePath = staticFilepath + "static/avatars/" + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            try {
                return Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("not exists");
            fileName = "user.jpg";
            filePath = staticFilepath + "static/avatars/" + fileName;
            file = new File(filePath);
            System.out.println("==========================exists+==========================" + file.toPath());
            try {
                return Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                System.out.println("fail");
                e.printStackTrace();
            }
        }
        return null;
        // 读取jar文件中的内容
        // String fileName = uid + ".jpg";

        // String fileRelativePath = "classpath:static/avatars/" + fileName;
        // Resource resource = resourceLoader.getResource(fileRelativePath);
        // System.out.println("文件名称: " + resource.getFilename());

        // if (resource.exists()) {
        // // 资源存在，进行相应操作
        // InputStream inputStream = resource.getInputStream();
        // try {
        // return inputStream.readAllBytes();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // } else {
        // // 文件不存在，处理文件不存在的情况
        // System.out.println("not exists");
        // fileName = "user.jpg";
        // fileRelativePath= "classpath:static/avatars/" + fileName;
        // resource = resourceLoader.getResource(fileRelativePath);
        // InputStream inputStream=resource.getInputStream();
        // System.out.println("==========================exists+=========================="
        // + resource.getFilename());
        // try {
        // byte[] b=inputStream.readAllBytes();
        // return b;
        // } catch (IOException e) {
        // System.out.println("fail");
        // e.printStackTrace();
        // }
        // }
        // return null;
    }
}
