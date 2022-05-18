package com.tsingeye.system.service.impl;

import com.tsingeye.common.config.MinioConfig;
import com.tsingeye.common.utils.SnowflakeGenerateIdUtil;
import com.tsingeye.common.utils.file.FileUploadUtils;
import com.tsingeye.common.utils.file.MinioUtil;
import com.tsingeye.system.service.ISysFileService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 文件上传
 *
 * @author 姜风
 * @date 2021/10/27 14:12
 */
@Service
public class SysFileServiceImpl implements ISysFileService {

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioUtil minioUtil;

    @Override
    public String uploadFileMinio(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("文件不存在！");
        }
        // 判断存储桶是否存在
        if (!minioUtil.bucketExists(minioConfig.getBucketName())) {
            minioUtil.makeBucket(minioConfig.getBucketName());
        }
        // 生成文件名
        String fineName = FileUploadUtils.extractFilename(file);
        try {
            // 上传文件
            minioUtil.upload(file, fineName, minioConfig.getBucketName());
        } catch (Exception e) {
            return null;
        }

        String url = "/" + minioConfig.getBucketName() + "/" + fineName;

        return url;
    }

    @Override
    public String storageFile(MultipartFile file) {
        // 首先判断是否存在该bucketName
        Boolean flag = minioUtil.bucketExists(minioConfig.getBucketName());
        // 不存在就创建
        if (!flag) {
            // 创建bucketName
            minioUtil.makeBucket(minioConfig.getBucketName());
        }
        // 获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 生成文件名
        String fileName = DateFormatUtils.format(new Date(), "yyyy/MM/dd")
                + "/" + SnowflakeGenerateIdUtil.getFlowIdInstance().nextId() + suffix;
        // 文件上传
        minioUtil.upload(file, fileName, minioConfig.getBucketName());
        // 上传成功的文件路径
        return "/" + minioConfig.getBucketName() + "/" + fileName;
    }
}
