package com.dx.trend.controller;

import com.dx.trend.pojo.IndexData;
import com.dx.trend.service.IndexDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * copyright:
 * Company: 上海悟泰信息科技有限公司
 *
 * @author dx
 * @since 2019/10/15 16:07
 */
@RestController
public class IndexDataController {
    @Resource
    IndexDataService indexDataService;

//  http://127.0.0.1:8001/freshIndexData/000300
//  http://127.0.0.1:8001/getIndexData/000300
//  http://127.0.0.1:8001/removeIndexData/000300

    @GetMapping("/freshIndexData/{code}")
    public String fresh(@PathVariable("code") String code)   {
        indexDataService.fresh(code);
        return "fresh index data successfully";
    }

    @GetMapping("/getIndexData/{code}")
    public List<IndexData> get(@PathVariable("code") String code)  {
        return indexDataService.get(code);
    }

    @GetMapping("/removeIndexData/{code}")
    public String remove(@PathVariable("code") String code)   {
        indexDataService.remove(code);
        return "remove index data successfully";
    }
}
