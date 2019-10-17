package com.dx.trend.service;

import com.dx.trend.client.IndexDataClient;
import com.dx.trend.pojo.IndexData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * copyright:
 * Company: 上海悟泰信息科技有限公司
 *
 * @author dx
 * @since 2019/10/17 9:54
 */

@Service
public class BackTestService {

    @Resource
    private IndexDataClient indexDataClient;

    public List<IndexData> listIndexData(String code){

        List<IndexData> indexDataList = indexDataClient.getIndexData(code);
        // 降序排序
        Collections.reverse(indexDataList);

        for (IndexData indexData : indexDataList) {
            System.out.println(indexData.getDate());
        }
        return indexDataList;
    }
}
