package com.dx.trend.controller;


import com.dx.trend.config.IpConfiguration;
import com.dx.trend.pojo.Index;
import com.dx.trend.service.IndexService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class IndexController {

    @Resource
    private IpConfiguration ipConfiguration;

    @Resource
    private IndexService indexService;


    @CrossOrigin
    @GetMapping("/codes")
    public List<Index> codes() {
        System.out.println("current instance's port is " + ipConfiguration.getPort());
        return indexService.get();
    }

}
