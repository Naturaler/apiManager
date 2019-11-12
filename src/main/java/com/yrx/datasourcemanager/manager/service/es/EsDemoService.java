package com.yrx.datasourcemanager.manager.service.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by r.x on 2019/11/11.
 */
@Service
@Slf4j
public class EsDemoService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public void testEs() {
        GetRequest request = new GetRequest("demo", "demo", "1");
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("getResponse.getSourceAsString() = " + getResponse.getSourceAsString());
    }

}
