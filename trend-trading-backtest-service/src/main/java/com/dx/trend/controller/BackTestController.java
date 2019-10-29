package com.dx.trend.controller;

import cn.hutool.core.date.DateUtil;
import com.dx.trend.pojo.IndexData;
import com.dx.trend.service.BackTestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * copyright:
 * Company: 上海悟泰信息科技有限公司
 *
 * @author dx
 * @since 2019/10/17 9:57
 */
@RestController
public class BackTestController {

    @Resource
    BackTestService backTestService;

    @GetMapping("/simulate/{code}/{startDate}/{endDate}")
    @CrossOrigin
    public Map<String,Object> backTest(@PathVariable("code") String code ,@PathVariable("startDate") String strStartDate
            ,@PathVariable("endDate") String strEndDate)   {
        List<IndexData> allIndexDatas = backTestService.listIndexData(code);

        List<IndexData> allIndexDataList = filterByDateRange(allIndexDatas, strStartDate, strEndDate);
        String indexStartDate =  allIndexDataList.get(0).getDate();
        String indexEndDate = allIndexDataList.get(allIndexDatas.size() -1).getDate();
        Map<String,Object> result = new HashMap<>(3);
        result.put("indexStartDate",indexStartDate);
        result.put("indexEndDate",indexEndDate);
        result.put("indexDatas", allIndexDataList);
        return result;
    }

    /**
     * 根据日期范围过滤数据
     * @param allIndexDatas 数据
     * @param strStartDate 开始日期
     * @param strEndDate 结束日期
     * @return 过滤后的数据
     */
    private List<IndexData> filterByDateRange (List<IndexData> allIndexDatas,String strStartDate ,String strEndDate){
        if(StringUtils.isEmpty(strEndDate) || StringUtils.isEmpty(strStartDate)){
            return allIndexDatas;
        }
        List<IndexData> result = new ArrayList<>(allIndexDatas.size());
        Date startDate = DateUtil.parse(strStartDate);
        Date endDate = DateUtil.parse(strEndDate);

        for (IndexData allIndexData : allIndexDatas) {
            Date date = DateUtil.parse(allIndexData.getDate());
            if(date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime()){
                result.add(allIndexData);
            }
        }
        return result;
    }


}
