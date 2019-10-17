package com.dx.trend.controller;

import com.dx.trend.pojo.IndexData;
import com.dx.trend.service.BackTestService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/simulate/{code}")
    @CrossOrigin
    public Map<String,Object> backTest(@PathVariable("code") String code)   {
        List<IndexData> allIndexDatas = backTestService.listIndexData(code);
        Map<String,Object> result = new HashMap<>(1);
        result.put("indexDatas", allIndexDatas);
        return result;
    }


}
