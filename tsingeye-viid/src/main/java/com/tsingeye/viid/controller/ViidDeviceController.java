package com.tsingeye.viid.controller;

import com.tsingeye.common.annotation.Log;
import com.tsingeye.common.core.controller.BaseController;
import com.tsingeye.common.core.domain.Result;
import com.tsingeye.common.core.page.TableDataInfo;
import com.tsingeye.common.enums.BusinessType;
import com.tsingeye.common.utils.poi.ExcelUtil;
import com.tsingeye.viid.domain.ViidDevice;
import com.tsingeye.viid.domain.ViidDeviceVo;
import com.tsingeye.viid.service.ViidDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 姜风
 */
@Api(tags = "设备信息")
@RestController
@RequestMapping("/viid/device")
public class ViidDeviceController extends BaseController {

    @Autowired
    private ViidDeviceService viidDeviceService;

    @ApiOperation(value = "获取设备列表" , notes = "获取设备列表的接口")
    @PreAuthorize("@ss.hasPermi('viid:device:list')")
    @RequestMapping(value = "/getDeviceList" , method = RequestMethod.GET)
    public Result getDeviceList() {
        List<ViidDeviceVo> deviceVos = viidDeviceService.getDeviceList();
        return Result.success(deviceVos);
    }

    /**
     * 查询设备信息列表
     */
    @ApiOperation(value = "查询设备信息列表" , notes = "查询设备信息列表的接口")
    @PreAuthorize("@ss.hasPermi('viid:device:list')")
    @GetMapping("/list")
    public TableDataInfo list(ViidDevice viidDevice) {
        startPage();
        List<ViidDevice> list = viidDeviceService.selectViidDeviceList(viidDevice);
        return getDataTable(list);
    }

    /**
     * 导出设备信息列表
     */
    @ApiOperation(value = "导出设备信息列表" , notes = "导出设备信息列表的接口")
    @PreAuthorize("@ss.hasPermi('viid:device:export')")
    @Log(title = "设备信息" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ViidDevice viidDevice) {
        List<ViidDevice> list = viidDeviceService.selectViidDeviceList(viidDevice);
        ExcelUtil<ViidDevice> util = new ExcelUtil<ViidDevice>(ViidDevice.class);
        util.exportExcel(response, list, "设备信息数据");
    }

    /**
     * 获取设备信息详细信息
     */
    @ApiOperation(value = "获取设备信息详细信息" , notes = "获取设备信息详细信息的接口")
    @ApiImplicitParam(name = "id" , value = "设备信息ID" , required = true, dataType = "String" , paramType = "path" , dataTypeClass = String.class)
    @PreAuthorize("@ss.hasPermi('viid:device:query')")
    @GetMapping(value = "/{id}")
    public Result getInfo(@PathVariable("id") String id) {
        return Result.success(viidDeviceService.selectViidDeviceById(id));
    }

    /**
     * 新增设备信息
     */
    @ApiOperation(value = "新增设备信息" , notes = "新增设备信息的接口")
    @PreAuthorize("@ss.hasPermi('viid:device:add')")
    @Log(title = "设备信息" , businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@RequestBody ViidDevice viidDevice) {
        return toAjax(viidDeviceService.insertViidDevice(viidDevice));
    }

    /**
     * 修改设备信息
     */
    @ApiOperation(value = "修改设备信息" , notes = "修改设备信息的接口")
    @PreAuthorize("@ss.hasPermi('viid:device:edit')")
    @Log(title = "设备信息" , businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody ViidDevice viidDevice) {
        return toAjax(viidDeviceService.updateViidDevice(viidDevice));
    }

    /**
     * 删除设备信息
     */
    @ApiOperation(value = "删除设备信息" , notes = "删除设备信息的接口")
    @ApiImplicitParam(name = "ids" , value = "设备信息ID" , required = true, dataType = "String" , paramType = "path" , dataTypeClass = String.class)
    @PreAuthorize("@ss.hasPermi('viid:device:remove')")
    @Log(title = "设备信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable String[] ids) {
        return toAjax(viidDeviceService.deleteViidDeviceByIds(ids));
    }
}
