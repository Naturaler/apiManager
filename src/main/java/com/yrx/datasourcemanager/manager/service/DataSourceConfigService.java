package com.yrx.datasourcemanager.manager.service;

import com.alibaba.fastjson.JSON;
import com.yrx.datasourcemanager.manager.constant.RequestType;
import com.yrx.datasourcemanager.manager.util.HttpUtil;
import com.yrx.datasourcemanager.manager.vo.DataSourceConfigVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by r.x on 2019/9/1.
 */
@Service
@Slf4j
public class DataSourceConfigService {

    public String config(DataSourceConfigVO vo) {
        RequestType requestType = vo.getRequestType();
        if (requestType == RequestType.POST_JSON) {
            System.out.println("--->" + HttpUtil.postJson(vo.getUrl(), JSON.toJSONString(vo.getParams())));
        }
        return "hello world";
    }

}
