package com.tsingeye.viid.mapper;

import com.tsingeye.viid.domain.SubImageInfo;
import com.tsingeye.viid.domain.ViidFaceImage;

import java.util.List;

public interface ViidFaceImageMapper {

    /**
     * 批量新增faceimages
     *
     * @param images 照片列表
     * @return 返回
     */
    public int insertBatch(List<SubImageInfo> images);

    /**
     * 查询人脸照片
     *
     * @param id 人脸照片主键
     * @return 人脸照片
     */
    public ViidFaceImage selectViidFaceImageById(String id);

    /**
     * 查询人脸照片列表
     *
     * @param viidFaceImage 人脸照片
     * @return 人脸照片集合
     */
    public List<ViidFaceImage> selectViidFaceImageList(ViidFaceImage viidFaceImage);

    /**
     * 新增人脸照片
     *
     * @param viidFaceImage 人脸照片
     * @return 结果
     */
    public int insertViidFaceImage(ViidFaceImage viidFaceImage);

    /**
     * 修改人脸照片
     *
     * @param viidFaceImage 人脸照片
     * @return 结果
     */
    public int updateViidFaceImage(ViidFaceImage viidFaceImage);

    /**
     * 删除人脸照片
     *
     * @param id 人脸照片主键
     * @return 结果
     */
    public int deleteViidFaceImageById(String id);

    /**
     * 批量删除人脸照片
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteViidFaceImageByIds(String[] ids);
}
