package com.dx.trend.job;

import cn.hutool.core.date.DateUtil;
import com.dx.trend.pojo.Index;
import com.dx.trend.service.IndexDataService;
import com.dx.trend.service.IndexService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dx
 */
public class IndexDataSyncJob extends QuartzJobBean {

    @Resource
    private IndexService indexService;

    @Resource
    private IndexDataService indexDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时启动：" + DateUtil.now());
        List<Index> indexes = indexService.fresh();
        for (Index index : indexes) {
            indexDataService.fresh(index.getCode());
        }
        System.out.println("定时结束：" + DateUtil.now());
    }
}
