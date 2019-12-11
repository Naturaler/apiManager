package com.yrx.dataSourceManager.apimanager.source;

import com.yrx.dataSourceManager.apimanager.source.api.IApi;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by r.x on 2019/12/11.
 */
@Slf4j
public class ApiFactory {
    private static Map<Integer, IApi> apiInstances = new HashMap<>();

    public IApi getApiByCode(Integer apiCode) {
        IApi iApi = apiInstances.get(apiCode);
        if (iApi != null) {
            return iApi;
        }
        // 能否弄成动态加载呢？
        log.error("apiCode:{} not found !", apiCode);
        throw new IllegalArgumentException("apiCode:[" + apiCode + "] not found !");
    }
}
