package com.dx.trend.client;

import java.util.List;

import com.dx.trend.pojo.IndexData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 * @author you
 */
@FeignClient(value = "INDEX-DATA-SERVICE",fallback = IndexDataClientFeignHystrix.class)
public interface IndexDataClient {


    @GetMapping("/data/{code}")
    List<IndexData> getIndexData(@PathVariable("code") String code);
}
