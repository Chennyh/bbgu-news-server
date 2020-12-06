package com.chennyh.bbgunews.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.chennyh.bbgunews.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chennyh
 * @date 2020/12/4 23:27
 * @description 图片工具类
 */
@Slf4j
@Component
public class ImgUtil {

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    public String saveImg(MultipartFile image) {
        //获取文件名
        String fileName = image.getOriginalFilename();
        //获取后缀名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        List<String> names = Arrays.asList(".gif", ".jfif", ".pjpeg", ".jpeg", ".pjp", ".jpg", ".png");
        if (names.stream().noneMatch(s -> s.contains(suffixName))) {
            throw new ApiException("仅支持图片格式");
        }
        //随机生成一个文件名
        fileName = IdUtil.simpleUUID() + suffixName;
        //创建文件
        File dest = new File(filePath + fileName);
        try {
            image.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("文件上传失败");
            throw new ApiException(e.getMessage());
        }
        log.warn("文件上传成功，路径为：{}", filePath + fileName);
        return "/images/" + fileName;
    }

    public boolean delImg(String imageName) {
        String path = filePath + imageName;
        if (FileUtil.isFile(path)) {
            FileUtil.del(path);
            return true;
        } else {
            throw new ApiException("图片不存在");
        }
    }
}
