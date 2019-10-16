package com.dx.trend.controller;


import com.dx.trend.config.IpConfiguration;
import com.dx.trend.pojo.IndexData;
import com.dx.trend.service.IndexDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dx
 */
@RestController
public class IndexDataController {

    @Resource
    private IpConfiguration ipConfiguration;

    @Resource
    private IndexDataService indexDataService;


    @CrossOrigin
    @GetMapping("/data/{code}")
    public List<IndexData> codes(@PathVariable("code") String code) {
        System.out.println("current instance's port is " + ipConfiguration.getPort());
        return indexDataService.get(code);
    }
}
