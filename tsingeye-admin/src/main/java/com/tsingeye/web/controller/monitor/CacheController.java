package com.tsingeye.web.controller.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tsingeye.common.core.domain.Result;
import com.tsingeye.common.utils.StringUtils;

/**
 * 缓存监控
 *
 * @author tsingeye
 */
@Api(tags = "缓存监控")
@RestController
@RequestMapping("/monitor/cache")
public class CacheController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "获取缓存监控信息",notes = "缓存监控接口")
    @PreAuthorize("@ss.hasPermi('monitor:cache:list')")
    @GetMapping()
    public Result getInfo() throws Exception {
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());
        Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());

        Map<String, Object> result = new HashMap<>(3);
        result.put("info" , info);
        result.put("dbSize" , dbSize);

        List<Map<String, String>> pieList = new ArrayList<>();
        commandStats.stringPropertyNames().forEach(key -> {
            Map<String, String> data = new HashMap<>(2);
            String property = commandStats.getProperty(key);
            data.put("name" , StringUtils.removeStart(key, "cmdstat_"));
            data.put("value" , StringUtils.substringBetween(property, "calls=" , ",usec"));
            pieList.add(data);
        });
        result.put("commandStats" , pieList);
        return Result.success(result);
    }
}
