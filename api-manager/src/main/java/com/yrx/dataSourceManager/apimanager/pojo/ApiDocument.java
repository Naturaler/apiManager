package com.yrx.dataSourceManager.apimanager.pojo;

import com.yrx.dataSourceManager.apimanager.constant.HttpType;
import com.yrx.dataSourceManager.apimanager.constant.ParamType;
import lombok.Data;

import java.util.Map;

/**
 * Created by r.x on 2019/12/2.
 * 接口文档
 */
@Data
public class ApiDocument {
    private String desc;
    private HttpType httpType;
    private String url;
    private Map<String, String> header;
    private ParamType paramType;
    private Object response;
}
