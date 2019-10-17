package com.dx.trend.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.dx.trend.pojo.IndexData;
import com.dx.trend.util.SpringContextUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
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
 * @since 2019/10/15 15:24
 */
@Service
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {
    @Resource
    RestTemplate restTemplate;

    private Map<String, List<IndexData>> indexDatas = new HashMap<>();

    public List<IndexData> fetch_indexes_from_third_part(String code){
        List<Map> temp= restTemplate.getForObject("http://127.0.0.1:8090/indexes/"+code+".json",List.class);
        return map2IndexData(temp);
    }

    private List<IndexData> map2IndexData(List<Map> temp) {
        List<IndexData> indexDatas = new ArrayList<>();
        for (Map map : temp) {
            String date = map.get("date").toString();
            float closePoint = Convert.toFloat(map.get("closePoint"));
            IndexData indexData = new IndexData();
            indexData.setDate(date);
            indexData.setClosePoint(closePoint);
            indexDatas.add(indexData);
        }

        return indexDatas;
    }

    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    public List<IndexData> fresh(String code) {
        List<IndexData> indexeDatas =fetch_indexes_from_third_part(code);

        indexDatas.put(code, indexeDatas);

        System.out.println("code:"+code);
        System.out.println("indexeDatas:"+indexDatas.get(code).size());

        IndexDataService indexDataService = SpringContextUtil.getBean(IndexDataService.class);
        indexDataService.remove(code);
        return indexDataService.store(code);
    }

    public List<IndexData> third_part_not_connected(String code){
        System.out.println("third_part_not_connected()");
        IndexData index= new IndexData();
        index.setClosePoint(0);
        index.setDate("n/a");
        return CollectionUtil.toList(index);
    }

    @Cacheable(key = "'indexData-code-'+#p0")
    public List<IndexData> get(String code) {
        return CollUtil.toList();
    }

    @Cacheable(key = "'indexData-code-'+#p0")
    public List<IndexData> store(String code) {
        return indexDatas.get(code);
    }

    @CacheEvict(key = "'indexData-code-'+#p0")
    public void remove(String code) {
    }

}
