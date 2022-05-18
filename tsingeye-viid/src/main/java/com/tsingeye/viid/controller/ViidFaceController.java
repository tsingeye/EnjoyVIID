package com.tsingeye.viid.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tsingeye.common.annotation.Log;
import com.tsingeye.common.config.MinioConfig;
import com.tsingeye.common.core.controller.BaseController;
import com.tsingeye.common.core.domain.Result;
import com.tsingeye.common.core.page.TableDataInfo;
import com.tsingeye.common.enums.BusinessType;
import com.tsingeye.common.utils.ImageUtils;
import com.tsingeye.common.utils.SnowflakeGenerateIdUtil;
import com.tsingeye.common.utils.file.FileUploadUtils;
import com.tsingeye.common.utils.file.MinioUtil;
import com.tsingeye.common.utils.poi.ExcelUtil;
import com.tsingeye.viid.domain.*;
import com.tsingeye.viid.service.ViidFaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 人脸对象接口
 *
 * @author 姜风
 */
@Api(tags = "人脸信息")
@RestController
@Slf4j
public class ViidFaceController extends BaseController {

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private ViidFaceService viidFaceService;


    /**
     * 批量新增人脸
     */
    @RequestMapping(value = "/VIID/Faces", method = RequestMethod.POST)
    public String faces(@RequestBody String faceInfo) {
        List<ViidFaceRequest> insertFaceList = new ArrayList<>();
        List<SubImageInfo> insertImageList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 返回参数
        JSONObject result = new JSONObject();
        JSONObject ju = new JSONObject();
        // 获取请求的object
        JSONObject jp = JSONObject.parseObject(faceInfo);
        // 从请求的Object中获取FaceListObject
        JSONObject faceListObject = (JSONObject) jp.get("FaceListObject");
        // 从FaceListObject中获取FaceObject
        JSONArray faceObject = faceListObject.getJSONArray("FaceObject");
        String FaceId = null;
        // 遍历人脸列表
        for (Object object : faceObject) {
            ViidFaceRequest viidFaceRequest = new ViidFaceRequest();
            JSONObject jsonObject = (JSONObject) object;
            // 获取人脸
            VdsFace face = JSONObject.parseObject(jsonObject.toJSONString(), VdsFace.class);
            // 设置face的id
            face.setID(String.valueOf(SnowflakeGenerateIdUtil.getFlowIdInstance().nextId()));
            // 设置FaceId
            FaceId = face.getFaceID();
            // 判断face中的创建时间
            if (ObjectUtil.isEmpty(face.getCreateTime())) {
                face.setCreateTime(new Timestamp(System.currentTimeMillis()));
            }
            // 获取图片信息
            SubImageInfoObject subImageList = face.getSubImageList();
            // 判断人脸对象中的列表是否为空
            if (ObjectUtil.isNotEmpty(subImageList) && CollUtil.isNotEmpty(subImageList.getSubImageInfoObject())) {
                // 遍历每个图片对象
                for (SubImage subImage : subImageList.getSubImageInfoObject()) {
                    // 新建SubImageInfo
                    SubImageInfo subImageInfo = new SubImageInfo();
                    // base64转换成MultipartFIle
                    MultipartFile file = ImageUtils.base64ToMultipartFile(subImage.getData());
                    // 上传minIO
                    String url = uploadFileMinio(file, subImage.getFileFormat());
                    log.info("**************************************************上传的图片地址为：{}", url);
                    // 设置主键
                    subImage.setId(String.valueOf(SnowflakeGenerateIdUtil.getFlowIdInstance().nextId()));
                    // 设置faceId
                    subImage.setFaceId(face.getFaceID());
                    // 设置base64的字节数组
                    subImage.setDataBytes(subImage.getData().getBytes(StandardCharsets.UTF_8));
                    // 设置minio的url
                    subImage.setImageUrl(url);
                    // 设置创建时间
                    subImage.setCreateTime(sdf.format(new Date()));
                    // 转换
                    convertImages(subImageInfo, subImage);
                    // 添加到照片列表中
                    insertImageList.add(subImageInfo);
                }
            }
            // 转换face
            convertFace(viidFaceRequest, face);
            // 添加到face列表
            insertFaceList.add(viidFaceRequest);
            log.info("设备ID：{}", face.getDeviceID());
        }
        // 批量新增人脸及人脸照片列表
        if (CollUtil.isNotEmpty(insertFaceList) && CollUtil.isNotEmpty(insertImageList)) {
            int insert = viidFaceService.batchInsertFacesAndFacesImages(insertFaceList, insertImageList);
        }
        ju.put("Id", FaceId);
        ju.put("StatusObject", 0);
        ju.put("RequestURL", "/VIID/Faces");
        ju.put("StatusString", "OK");
        result.put("ResponseStatusObject", ju);
        log.info("打印返回的请求参数：{}", result.toJSONString());

        return result.toJSONString();
    }

    private void convertFace(ViidFaceRequest viidFaceRequest, VdsFace face) {
        viidFaceRequest.setId(face.getID());
        viidFaceRequest.setFaceId(face.getFaceID());
        viidFaceRequest.setInfoKind(face.getInfoKind());
        viidFaceRequest.setSourceId(face.getSourceID());
        viidFaceRequest.setDeviceId(face.getDeviceID());
        viidFaceRequest.setShotTime(face.getShotTime());
        viidFaceRequest.setLeftTopX(face.getLeftTopX());
        viidFaceRequest.setLeftTopY(face.getLeftTopY());
        viidFaceRequest.setRightBtmX(face.getRightBtmX());
        viidFaceRequest.setRightBtmY(face.getRightBtmY());
        viidFaceRequest.setLocationMarkTime(face.getLocationMarkTime());
        viidFaceRequest.setFaceAppearTime(face.getFaceAppearTime());
        viidFaceRequest.setFaceDisAppearTime(face.getFaceDisAppearTime());
        viidFaceRequest.setGenderCode(face.getGenderCode());
        viidFaceRequest.setAgeUpLimit(face.getAgeUpLimit());
        viidFaceRequest.setAgeLowerLimit(face.getAgeLowerLimit());
        viidFaceRequest.setGlassStyle(face.getGlassStyle());
        viidFaceRequest.setEmotion(face.getEmotion());
        viidFaceRequest.setIsDriver(face.getIsDriver());
        viidFaceRequest.setIsForeigner(face.getIsForeigner());
        viidFaceRequest.setIsSuspectedTerrorist(face.getIsSuspectedTerrorist());
        viidFaceRequest.setIsCriminalInvolved(face.getIsCriminalInvolved());
        viidFaceRequest.setIsDetainess(face.getIsDetainees());
        viidFaceRequest.setIsVictim(face.getIsVictim());
        viidFaceRequest.setIsSuspiciousPerson(face.getIsSuspiciousPerson());
        viidFaceRequest.setSimilaritydegree(face.getSimilaritydegree());
        viidFaceRequest.setCreateTime(face.getCreateTime());
    }

    private void convertImages(SubImageInfo subImageInfo, SubImage subImage) {
        subImageInfo.setId(subImage.getId());
        subImageInfo.setImageUrl(subImage.getImageUrl());
        subImageInfo.setFaceId(subImage.getFaceId());
        subImageInfo.setEventSort(subImage.getEventSort());
        subImageInfo.setDeviceId(subImage.getDeviceID());
        subImageInfo.setStoragePath(subImage.getStoragePath());
        subImageInfo.setType(subImage.getType());
        subImageInfo.setFileFormat(subImage.getFileFormat());
        subImageInfo.setShotTime(subImage.getShotTime());
        subImageInfo.setWidth(subImage.getWidth());
        subImageInfo.setHeight(subImage.getHeight());
        subImageInfo.setDataBytes(subImage.getDataBytes());
        subImageInfo.setCreateTime(subImage.getCreateTime());
    }


    /**
     * minio图片上传
     *
     * @param file file
     * @return 地址
     */
    public String uploadFileMinio(MultipartFile file, String fileFormat) {
        if (file.isEmpty()) {
            throw new RuntimeException("文件不存在！");
        }
        // 判断存储桶是否存在
        if (!minioUtil.bucketExists(minioConfig.getBucketName())) {
            minioUtil.makeBucket(minioConfig.getBucketName());
        }
        if ("Jpeg".equals(fileFormat)) {
            fileFormat = "jpg";
        }
        // 生成文件名
        String fineName = FileUploadUtils.extractFilename(file) + "." + fileFormat;
        try {
            // 上传文件
            Boolean upload = minioUtil.upload(file, fineName, minioConfig.getBucketName());
            // 判断上传是否成功
            if (!upload) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

        return "/" + minioConfig.getBucketName() + "/" + fineName;
    }

    /**
     * 查询人脸信息列表
     */
    @ApiOperation(value = "人脸照片分页查询", notes = "分页查询")
    @PreAuthorize("@ss.hasPermi('viid:face:list')")
    @GetMapping("/viid/face/list")
    public TableDataInfo list(ViidFace viidFace) {
        startPage();
        List<ViidFace> list = viidFaceService.selectViidFaceList(viidFace);
        return getDataTable(list);
    }

    /**
     * 导出人脸信息列表
     */
    @ApiOperation(value = "导出人脸照片", notes = "导出")
    @PreAuthorize("@ss.hasPermi('viid:face:export')")
    @Log(title = "人脸信息", businessType = BusinessType.EXPORT)
    @PostMapping("/viid/face/export")
    public void export(HttpServletResponse response, ViidFace viidFace) {
        List<ViidFace> list = viidFaceService.selectViidFaceList(viidFace);
        ExcelUtil<ViidFace> util = new ExcelUtil<ViidFace>(ViidFace.class);
        util.exportExcel(response, list, "人脸信息数据");
    }

    /**
     * 获取人脸信息详细信息
     */
    @ApiOperation(value = "查询人脸信息", notes = "人脸信息查询接口")
    @ApiImplicitParam(name = "id", value = "对象id", required = true, dataType = "String", paramType = "path", dataTypeClass = String.class)
    @PreAuthorize("@ss.hasPermi('viid:face:query')")
    @GetMapping(value = "/viid/face/{id}")
    public Result getInfo(@PathVariable("id") String id) {
        return Result.success(viidFaceService.selectViidFaceById(id));
    }

    /**
     * 新增人脸信息
     */
    @ApiOperation(value = "新增人脸信息", notes = "人脸信息的新增接口")
    @PreAuthorize("@ss.hasPermi('viid:face:add')")
    @Log(title = "人脸信息", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/viid/face", method = RequestMethod.POST)
    public Result add(@RequestBody ViidFace viidFace) {
        return toAjax(viidFaceService.insertViidFace(viidFace));
    }

    /**
     * 修改人脸信息
     */
    @ApiOperation(value = "修改人脸信息", notes = "人脸信息的修改接口")
    @PreAuthorize("@ss.hasPermi('viid:face:edit')")
    @Log(title = "人脸信息", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/viid/face", method = RequestMethod.PUT)
    public Result edit(@RequestBody ViidFace viidFace) {
        return toAjax(viidFaceService.updateViidFace(viidFace));
    }

    /**
     * 删除人脸信息
     */
    @ApiOperation(value = "删除人脸信息", notes = "人脸信息的删除接口")
    @ApiImplicitParam(name = "ids", value = "对象id列表", required = true, dataType = "List", paramType = "path", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermi('viid:face:remove')")
    @Log(title = "人脸信息", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/viid/face/{ids}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String[] ids) {
        return toAjax(viidFaceService.deleteViidFaceByIds(ids));
    }
}
