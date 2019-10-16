package com.dx.trend.service;


import cn.hutool.core.collection.CollUtil;
import com.dx.trend.pojo.Index;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "indexes")
public class IndexService {

    @Cacheable(key = "'all_codes'")
    public List<Index> get(){
        Index index = new Index();
        index.setCode("000000");
        index.setName("無效的指數代碼");
        return CollUtil.toList();
    }

}
