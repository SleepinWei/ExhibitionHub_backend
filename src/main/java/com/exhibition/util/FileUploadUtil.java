package com.exhibition.util;

import java.io.File;
import java.io.IOException;

import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;

public class FileUploadUtil {
    /**
     * 上传文件
     * 
     * @param multipartFile
     * @return 文件存储路径
     */
    public static String upload(MultipartFile multipartFile, String staticpath, int uid) {
        // 获取原始文件名
        String originalFilename = multipartFile.getOriginalFilename();
        // 获取原始文件名的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 拼接新的文件名
        String newFileName = uid + suffix;
        // 文件存储位置，文件的目录要存在才行，可以先创建文件目录，然后进行存储
        File path = new File("");
        String classespath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String filePath = classespath + staticpath + uid + ".jpg";
        System.out.println("lxcr2+++++++++++++" + filePath);
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

    public static byte[] load(int uid) {
        String fileName = uid + ".jpg";
        String classespath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String filePath = classespath + "static/avatars/" + fileName;
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
            filePath = classespath + "static/avatars/" + fileName;
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
    }
}
