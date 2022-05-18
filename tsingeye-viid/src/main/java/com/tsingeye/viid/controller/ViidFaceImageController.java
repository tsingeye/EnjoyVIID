package com.tsingeye.viid.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.tsingeye.common.config.MinioConfig;
import com.tsingeye.common.utils.StringUtils;
import com.tsingeye.viid.service.ViidFaceImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tsingeye.common.annotation.Log;
import com.tsingeye.common.core.controller.BaseController;
import com.tsingeye.common.core.domain.Result;
import com.tsingeye.common.enums.BusinessType;
import com.tsingeye.viid.domain.ViidFaceImage;
import com.tsingeye.common.utils.poi.ExcelUtil;
import com.tsingeye.common.core.page.TableDataInfo;

/**
 * 人脸照片Controller
 *
 * @author 姜风
 * @date 2022-04-21
 */
@Api(tags = "人脸照片")
@RestController
@RequestMapping("/viid/image")
public class ViidFaceImageController extends BaseController {

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private ViidFaceImageService viidFaceImageService;

    /**
     * 查询人脸照片列表
     */
    @ApiOperation(value = "分页查询人脸图片" , notes = "分页查询接口")
    @PreAuthorize("@ss.hasPermi('viid:image:list')")
    @GetMapping("/list")
    public TableDataInfo list(ViidFaceImage viidFaceImage) {
        startPage();
        List<ViidFaceImage> list = viidFaceImageService.selectViidFaceImageList(viidFaceImage);
        // 遍历list
        if (CollUtil.isNotEmpty(list)) {
            for (ViidFaceImage faceImage : list) {
                if (StringUtils.isNotEmpty(faceImage.getImageUrl())) {
                    faceImage.setImageUrl(minioConfig.getUrl() + faceImage.getImageUrl());
                }
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出人脸照片列表
     */
    @ApiOperation(value = "导出人脸照片" , notes = "人脸照片导出接口")
    @PreAuthorize("@ss.hasPermi('viid:image:export')")
    @Log(title = "人脸照片" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ViidFaceImage viidFaceImage) {
        List<ViidFaceImage> list = viidFaceImageService.selectViidFaceImageList(viidFaceImage);
        ExcelUtil<ViidFaceImage> util = new ExcelUtil<ViidFaceImage>(ViidFaceImage.class);
        util.exportExcel(response, list, "人脸照片数据");
    }

    /**
     * 获取人脸照片详细信息
     */
    @ApiOperation(value = "获取人脸照片详情" , notes = "人脸照片获取接口")
    @ApiImplicitParam(name = "id" , value = "编号" , required = true, dataType = "String" , paramType = "path" , dataTypeClass = String.class)
    @PreAuthorize("@ss.hasPermi('viid:image:query')")
    @GetMapping(value = "/{id}")
    public Result getInfo(@PathVariable("id") String id) {
        return Result.success(viidFaceImageService.selectViidFaceImageById(id));
    }

    /**
     * 新增人脸照片
     */
    @ApiOperation(value = "新增人脸照片" , notes = "人脸照片新增接口")
    @PreAuthorize("@ss.hasPermi('viid:image:add')")
    @Log(title = "人脸照片" , businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@RequestBody ViidFaceImage viidFaceImage) {
        return toAjax(viidFaceImageService.insertViidFaceImage(viidFaceImage));
    }

    /**
     * 修改人脸照片
     */
    @ApiOperation(value = "修改人脸照片" , notes = "人脸照片修改接口")
    @PreAuthorize("@ss.hasPermi('viid:image:edit')")
    @Log(title = "人脸照片" , businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody ViidFaceImage viidFaceImage) {
        return toAjax(viidFaceImageService.updateViidFaceImage(viidFaceImage));
    }

    /**
     * 删除人脸照片
     */
    @ApiOperation(value = "删除人脸照片" , notes = "人脸照片删除接口")
    @ApiImplicitParam(name = "ids" , value = "编号" , required = true, dataType = "List" , paramType = "path" , dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermi('viid:image:remove')")
    @Log(title = "人脸照片" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable String[] ids) {
        return toAjax(viidFaceImageService.deleteViidFaceImageByIds(ids));
    }
}
