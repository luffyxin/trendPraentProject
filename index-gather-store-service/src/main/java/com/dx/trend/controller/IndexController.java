package com.dx.trend.controller;
import java.util.List;

import com.dx.trend.pojo.Index;
import com.dx.trend.service.IndexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class IndexController {
    @Resource
    IndexService indexService;

    @GetMapping("/getCodes")
    public List<Index> get() throws Exception {
        return indexService.fetch_indexes_from_third_part();
    }
}
