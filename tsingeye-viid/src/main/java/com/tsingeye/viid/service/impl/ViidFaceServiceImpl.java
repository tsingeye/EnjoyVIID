package com.tsingeye.viid.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tsingeye.common.utils.SnowflakeGenerateIdUtil;
import com.tsingeye.viid.domain.SubImageInfo;
import com.tsingeye.viid.domain.ViidFace;
import com.tsingeye.viid.domain.ViidFaceRequest;
import com.tsingeye.viid.mapper.ViidFaceImageMapper;
import com.tsingeye.viid.mapper.ViidFaceMapper;
import com.tsingeye.viid.service.ViidFaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ViidFaceServiceImpl implements ViidFaceService {

    @Autowired
    private ViidFaceMapper viidFaceMapper;

    @Autowired
    private ViidFaceImageMapper viidFaceImageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsertFacesAndFacesImages(List<ViidFaceRequest> faceObject,List<SubImageInfo> subImageInfoList) {
        // 批量新增
        int faceInsert = viidFaceMapper.insertBatch(faceObject);

        int faceImageInsert = 0;
        // 判断不为空且face添加成功
        if (CollUtil.isNotEmpty(subImageInfoList) && faceInsert > 0) {
            faceImageInsert = viidFaceImageMapper.insertBatch(subImageInfoList);
        }

        return faceImageInsert;
    }

    /**
     * 查询人脸信息
     *
     * @param id 人脸信息主键
     * @return 人脸信息
     */
    @Override
    public ViidFace selectViidFaceById(String id) {
        return viidFaceMapper.selectViidFaceById(id);
    }

    /**
     * 查询人脸信息列表
     *
     * @param viidFace 人脸信息
     * @return 人脸信息
     */
    @Override
    public List<ViidFace> selectViidFaceList(ViidFace viidFace) {
        return viidFaceMapper.selectViidFaceList(viidFace);
    }

    /**
     * 新增人脸信息
     *
     * @param viidFace 人脸信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertViidFace(ViidFace viidFace) {
        viidFace.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return viidFaceMapper.insertViidFace(viidFace);
    }

    /**
     * 修改人脸信息
     *
     * @param viidFace 人脸信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateViidFace(ViidFace viidFace) {
        viidFace.setId(String.valueOf(SnowflakeGenerateIdUtil.getFlowIdInstance().nextId()));
        viidFace.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return viidFaceMapper.updateViidFace(viidFace);
    }

    /**
     * 批量删除人脸信息
     *
     * @param ids 需要删除的人脸信息主键
     * @return 结果
     */
    @Override
    public int deleteViidFaceByIds(String[] ids) {
        return viidFaceMapper.deleteViidFaceByIds(ids);
    }

    /**
     * 删除人脸信息信息
     *
     * @param id 人脸信息主键
     * @return 结果
     */
    @Override
    public int deleteViidFaceById(String id) {
        return viidFaceMapper.deleteViidFaceById(id);
    }
}
