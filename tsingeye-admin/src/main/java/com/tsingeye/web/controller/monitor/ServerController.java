package com.tsingeye.web.controller.monitor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tsingeye.common.core.domain.Result;
import com.tsingeye.framework.web.domain.Server;

/**
 * 服务器监控
 *
 * @author tsingeye
 */
@Api(tags = "服务器监控")
@RestController
@RequestMapping("/monitor/server")
public class ServerController {

    @ApiOperation(value = "获取服务器监控信息",notes = "服务器监控信息接口")
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public Result getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return Result.success(server);
    }
}
