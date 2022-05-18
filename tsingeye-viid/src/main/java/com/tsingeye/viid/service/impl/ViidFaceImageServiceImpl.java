package com.tsingeye.viid.service.impl;

import java.util.List;

import com.tsingeye.common.utils.DateUtils;
import com.tsingeye.viid.service.ViidFaceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingeye.viid.mapper.ViidFaceImageMapper;
import com.tsingeye.viid.domain.ViidFaceImage;

/**
 * 人脸照片Service业务层处理
 *
 * @author 姜风
 * @date 2022-04-21
 */
@Service
public class ViidFaceImageServiceImpl implements ViidFaceImageService {
    @Autowired
    private ViidFaceImageMapper viidFaceImageMapper;

    /**
     * 查询人脸照片
     *
     * @param id 人脸照片主键
     * @return 人脸照片
     */
    @Override
    public ViidFaceImage selectViidFaceImageById(String id) {
        return viidFaceImageMapper.selectViidFaceImageById(id);
    }

    /**
     * 查询人脸照片列表
     *
     * @param viidFaceImage 人脸照片
     * @return 人脸照片
     */
    @Override
    public List<ViidFaceImage> selectViidFaceImageList(ViidFaceImage viidFaceImage) {
        return viidFaceImageMapper.selectViidFaceImageList(viidFaceImage);
    }

    /**
     * 新增人脸照片
     *
     * @param viidFaceImage 人脸照片
     * @return 结果
     */
    @Override
    public int insertViidFaceImage(ViidFaceImage viidFaceImage) {
        viidFaceImage.setCreateTime(DateUtils.getNowDate());
        return viidFaceImageMapper.insertViidFaceImage(viidFaceImage);
    }

    /**
     * 修改人脸照片
     *
     * @param viidFaceImage 人脸照片
     * @return 结果
     */
    @Override
    public int updateViidFaceImage(ViidFaceImage viidFaceImage) {
        return viidFaceImageMapper.updateViidFaceImage(viidFaceImage);
    }

    /**
     * 批量删除人脸照片
     *
     * @param ids 需要删除的人脸照片主键
     * @return 结果
     */
    @Override
    public int deleteViidFaceImageByIds(String[] ids) {
        return viidFaceImageMapper.deleteViidFaceImageByIds(ids);
    }

    /**
     * 删除人脸照片信息
     *
     * @param id 人脸照片主键
     * @return 结果
     */
    @Override
    public int deleteViidFaceImageById(String id) {
        return viidFaceImageMapper.deleteViidFaceImageById(id);
    }
}
