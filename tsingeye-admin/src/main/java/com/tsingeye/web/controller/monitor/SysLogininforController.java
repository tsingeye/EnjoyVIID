package com.tsingeye.web.controller.monitor;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tsingeye.common.annotation.Log;
import com.tsingeye.common.core.controller.BaseController;
import com.tsingeye.common.core.domain.Result;
import com.tsingeye.common.core.page.TableDataInfo;
import com.tsingeye.common.enums.BusinessType;
import com.tsingeye.common.utils.poi.ExcelUtil;
import com.tsingeye.system.domain.SysLogininfor;
import com.tsingeye.system.service.ISysLogininforService;

/**
 * 系统访问记录
 *
 * @author tsingeye
 */
@Api(tags = "系统访问记录")
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController {
    @Autowired
    private ISysLogininforService logininforService;

    @ApiOperation(value = "系统访问记录分页查询",notes = "系统访问记录分页查询接口")
    @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLogininfor logininfor) {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

    @ApiOperation(value = "导出登录日志",notes = "登录日志导出接口")
    @Log(title = "登录日志" , businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLogininfor logininfor) {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        util.exportExcel(response, list, "登录日志");
    }

    @ApiOperation(value = "删除登录日志",notes = "登录日志删除接口")
    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public Result remove(@PathVariable Long[] infoIds) {
        return toAjax(logininforService.deleteLogininforByIds(infoIds));
    }

    @ApiOperation(value = "清空登录日志",notes = "登录日志清空接口")
    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志" , businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public Result clean() {
        logininforService.cleanLogininfor();
        return Result.success();
    }
}
