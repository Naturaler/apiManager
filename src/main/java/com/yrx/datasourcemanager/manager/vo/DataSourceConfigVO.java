package com.yrx.datasourcemanager.manager.vo;

import com.yrx.datasourcemanager.manager.constant.RequestType;
import lombok.Data;

import java.util.List;

/**
 * Created by r.x on 2019/9/1.
 * 数据源配置
 */
@Data
public class DataSourceConfigVO {
    private String url;
    private RequestType requestType;
    private List<DataSourceConfigParam> params;

    @Data
    public static class DataSourceConfigParam {
        private String key;
        private Object value;
    }
}
