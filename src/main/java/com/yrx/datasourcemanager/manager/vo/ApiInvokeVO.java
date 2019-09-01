package com.yrx.datasourcemanager.manager.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by r.x on 2019/9/1.
 */
@Data
public class ApiInvokeVO {
    private Integer apiId;
    private List<ParamVO> params;

    @Data
    public static class ParamVO {
        private String paramName;
        private String paramType;
        private Object paramValue;
    }
}
