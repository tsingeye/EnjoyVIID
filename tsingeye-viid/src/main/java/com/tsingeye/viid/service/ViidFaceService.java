package com.tsingeye.viid.service;

import com.tsingeye.viid.domain.SubImageInfo;
import com.tsingeye.viid.domain.ViidFace;
import com.tsingeye.viid.domain.ViidFaceRequest;

import java.util.List;

public interface ViidFaceService {
    /**
     * 批量新增人脸和人脸照片
     * @param faceObject 人脸列表
     * @param subImageInfoList 人脸照片列表
     * @return 返回
     */
    int batchInsertFacesAndFacesImages(List<ViidFaceRequest> faceObject, List<SubImageInfo> subImageInfoList);

    /**
     * 查询人脸信息
     *
     * @param id 人脸信息主键
     * @return 人脸信息
     */
    public ViidFace selectViidFaceById(String id);

    /**
     * 查询人脸信息列表
     *
     * @param viidFace 人脸信息
     * @return 人脸信息集合
     */
    public List<ViidFace> selectViidFaceList(ViidFace viidFace);

    /**
     * 新增人脸信息
     *
     * @param viidFace 人脸信息
     * @return 结果
     */
    public int insertViidFace(ViidFace viidFace);

    /**
     * 修改人脸信息
     *
     * @param viidFace 人脸信息
     * @return 结果
     */
    public int updateViidFace(ViidFace viidFace);

    /**
     * 批量删除人脸信息
     *
     * @param ids 需要删除的人脸信息主键集合
     * @return 结果
     */
    public int deleteViidFaceByIds(String[] ids);

    /**
     * 删除人脸信息信息
     *
     * @param id 人脸信息主键
     * @return 结果
     */
    public int deleteViidFaceById(String id);
}
