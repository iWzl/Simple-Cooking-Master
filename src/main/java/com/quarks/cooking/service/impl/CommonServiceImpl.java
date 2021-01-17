package com.quarks.cooking.service.impl;

import com.quarks.cooking.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Value("${leonardo.filepath.image}")
    private String imageFilePath;

    @Override
    public String uploadMultipartFile(MultipartFile imageFile) {
        try {
            // 获取文件名称
            String fileName = imageFile.getOriginalFilename();
            //获取文件名称的后缀
            String suffixName = fileName != null ? fileName.substring(fileName.lastIndexOf(".")) : ".jpg";
            String newFileName = String.format("%s%s",UUID.randomUUID().toString(),suffixName);
            //设置文件存储路径
            String path = imageFilePath + newFileName;
            log.debug("Uploaded file name: {} Modified file name: {} Full file path: {}",fileName,newFileName,path);
            File dest = new File(path);
            //检测目录是否存在
            if (!dest.getParentFile().exists()) {
                if(dest.getParentFile().mkdirs()){
                    log.info("create file directory successfully");
                }
            }
            imageFile.transferTo(dest);
            return newFileName;
        } catch (IOException ex) {
            log.error("file save failed",ex);
            return "";
        }
    }
}
