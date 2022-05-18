package com.tsingeye.system.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传服务
 *
 * @author 姜风
 * @date 2021/10/27 14:11
 */
public interface ISysFileService {

    /**
     * 上传文件到minio
     *
     * @param file 文件
     * @return 返回地址
     */
    String uploadFileMinio(MultipartFile file);

    /**
     * 文件上传
     * @param file 文件
     * @return 文件地址
     */
    String storageFile(MultipartFile file);
}
