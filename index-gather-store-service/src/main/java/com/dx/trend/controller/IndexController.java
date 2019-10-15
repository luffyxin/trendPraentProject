package com.dx.trend.controller;
import java.util.List;

import com.dx.trend.pojo.Index;
import com.dx.trend.service.IndexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author you
 */
@RestController
public class IndexController {
    @Resource
    IndexService indexService;

    @GetMapping("/getCodes")
    public List<Index> get()   {
        return indexService.get();
    }

    @GetMapping("/freshCodes")
    public List<Index> fresh()  {
        return indexService.fresh();
    }

    @GetMapping("/removeCodes")
    public String remove()   {
        indexService.remove();
        return "remove codes successfully";
    }
}
