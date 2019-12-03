package com.yrx.dataSourceManager.apimanager.pojo;

import com.yrx.dataSourceManager.apimanager.constant.HttpType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by r.x on 2019/12/2.
 * 接口文档
 */
@Data
@Builder
public class ApiDocument {
    private String desc;
    private HttpType httpType;
    private String url;
    private Map<String, String> header;
    private List<ParamDescriptor> params;
    private Class<?> response;

    @Override
    public String toString() {
        return "ApiDocument{" +
                "desc='" + desc + '\'' +
                ", httpType=" + httpType +
                ", url='" + url + '\'' +
                ", header=" + header +
                ", params=" + params +
                ", response=" + response +
                '}';
    }
}
