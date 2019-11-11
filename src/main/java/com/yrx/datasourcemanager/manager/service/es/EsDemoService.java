package com.yrx.datasourcemanager.manager.service.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by r.x on 2019/11/11.
 */
@Service
@Slf4j
public class EsDemoService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;


}
