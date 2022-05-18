package com.tsingeye.quartz.util;

import org.quartz.JobExecutionContext;
import com.tsingeye.quartz.domain.SysJob;

/**
 * 定时任务处理（允许并发执行）
 *
 * @author tsingeye
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
